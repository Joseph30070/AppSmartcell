package com.example.smartcellapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PagosActivity : AppCompatActivity() {

    private lateinit var tvTotalPeriodo: TextView
    private lateinit var listaCuotas: LinearLayout
    private lateinit var tvDuracionCurso: TextView
    private var totalPeriodo = 0.0
    private val montoCuota = 200.0

    private val cursos = mapOf(
        "Robótica" to 4,
        "Ofimática" to 3,
        "Electrónica" to 4,
        "Reparación de Celulares" to 4,
        "Reparación de PCs y Laptop" to 4
    )

    private val prefs by lazy {
        getSharedPreferences("PagosPrefs", MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pagos)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbarPagos)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        tvTotalPeriodo = findViewById(R.id.tvTotalPeriodo)
        listaCuotas = findViewById(R.id.listaCuotas)
        tvDuracionCurso = findViewById(R.id.tvDuracionCurso)

        val spinnerCursos: Spinner = findViewById(R.id.spinnerCursos)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            listOf("Por favor seleccione su curso") + cursos.keys.toList()
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCursos.adapter = adapter

        spinnerCursos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val cursoSeleccionado = parent.getItemAtPosition(position).toString()
                if (cursoSeleccionado == "Por favor seleccione su curso") {
                    listaCuotas.removeAllViews()
                    tvDuracionCurso.text = ""
                } else {
                    mostrarCuotas(cursoSeleccionado)
                    tvDuracionCurso.text = "Duración del curso: ${cursos[cursoSeleccionado]} meses"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun mostrarCuotas(curso: String) {
        listaCuotas.removeAllViews()
        totalPeriodo = 0.0

        val cantidadCuotas = cursos[curso] ?: 0
        val inflater = LayoutInflater.from(this)

        for (i in 1..cantidadCuotas) {
            val cuotaView = inflater.inflate(R.layout.item_cuota, listaCuotas, false)
            val tvDescripcionCuota: TextView = cuotaView.findViewById(R.id.tvDescripcionCuota)
            val switchCuota: Switch = cuotaView.findViewById(R.id.switchCuota)

            tvDescripcionCuota.text = "Cuota $i - S/. $montoCuota\nVence: 30/${i.toString().padStart(2, '0')}/2025"

            // Recuperar estado guardado
            val key = "${curso}_cuota$i"
            val pagado = prefs.getBoolean(key, false)
            switchCuota.isChecked = pagado
            switchCuota.text = if (pagado) "Pagado" else "Pendiente"
            if (pagado) totalPeriodo += montoCuota

            configurarSwitch(switchCuota, curso, i)
            listaCuotas.addView(cuotaView)
        }

        actualizarTotal()
    }

    private fun configurarSwitch(switch: Switch, curso: String, numCuota: Int) {
        val key = "${curso}_cuota$numCuota"
        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                switch.text = "Pagado"
                totalPeriodo += montoCuota
            } else {
                switch.text = "Pendiente"
                totalPeriodo -= montoCuota
            }
            prefs.edit().putBoolean(key, isChecked).apply()
            actualizarTotal()
        }
    }

    private fun actualizarTotal() {
        tvTotalPeriodo.text = "Total del Periodo\nS/. %.2f".format(totalPeriodo)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
