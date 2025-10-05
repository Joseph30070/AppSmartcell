package com.example.smartcellapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Spinner

class PagosActivity : AppCompatActivity() {

    private lateinit var tvTotalPeriodo: TextView
    private lateinit var listaCuotas: LinearLayout
    private lateinit var tvDuracionCurso: TextView
    private var totalPeriodo = 0.0
    private val montoCuota = 200.0

    // Cursos y sus cuotas
    private val cursos = mapOf(
        "Robótica Avanzada" to 3,
        "Electrónica Digital" to 2,
        "Reparación de Laptops" to 3,
        "Reparación de PCs" to 4
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagos)

        // Ajustes iniciales
        val toolbar = findViewById<Toolbar>(R.id.toolbarPagos)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        tvTotalPeriodo = findViewById(R.id.tvTotalPeriodo)
        listaCuotas = findViewById(R.id.listaCuotas)
        tvDuracionCurso = findViewById(R.id.tvDuracionCurso)

        // Spinner de cursos
        val spinnerCursos: Spinner = findViewById(R.id.spinnerCursos)
        val listaCursos = mutableListOf("Seleccione su curso a pagar")
        listaCursos.addAll(cursos.keys)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaCursos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCursos.adapter = adapter

        // Listener del Spinner
        spinnerCursos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val cursoSeleccionado = parent.getItemAtPosition(position).toString()

                if (cursoSeleccionado == "Seleccione su curso a pagar") {
                    listaCuotas.removeAllViews()
                    tvDuracionCurso.text = "Duración del curso:"
                    totalPeriodo = 0.0
                    actualizarTotal()
                } else {
                    mostrarCuotas(cursoSeleccionado)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    // Genera dinámicamente las cuotas según el curso
    private fun mostrarCuotas(curso: String) {
        listaCuotas.removeAllViews()
        totalPeriodo = 0.0

        val cantidadCuotas = cursos[curso] ?: 0
        val inflater = LayoutInflater.from(this)

        // Mostrar duración arriba de las cuotas
        tvDuracionCurso.text = "Duración del curso: $cantidadCuotas meses"

        for (i in 1..cantidadCuotas) {
            val cuotaView = inflater.inflate(R.layout.item_cuota, listaCuotas, false)

            val tvInfo: TextView = cuotaView.findViewById(R.id.tvDescripcionCuota)
            val switch: Switch = cuotaView.findViewById(R.id.switchCuota)

            tvInfo.text = "Cuota $i - S/. $montoCuota\nVence: 30/${i.toString().padStart(2,'0')}/2025"

            configurarSwitch(switch)
            listaCuotas.addView(cuotaView)
        }

        actualizarTotal()
    }

    private fun configurarSwitch(switch: Switch) {
        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                switch.text = "Pagado"
                totalPeriodo += montoCuota
            } else {
                switch.text = "Pendiente"
                totalPeriodo -= montoCuota
            }
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
