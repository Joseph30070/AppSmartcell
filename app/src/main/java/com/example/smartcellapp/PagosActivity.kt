package com.example.smartcellapp

import android.os.Bundle
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PagosActivity : AppCompatActivity() {

    private lateinit var tvTotalPeriodo: TextView
    private var totalPeriodo = 0.0
    private val montoCuota = 200.0  // cada cuota cuesta 200 por ahora como ejemplo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pagos)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configuramos el Toolbar como ActionBar
        val toolbar = findViewById<Toolbar>(R.id.toolbarPagos)
        setSupportActionBar(toolbar)

        // Activamos la flecha de retroceso
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Inicializamos el TextView del total
        tvTotalPeriodo = findViewById(R.id.tvTotalPeriodo)

        // Switches de cuotas proximanmente hacer que se generern automaticamente
        val switchCuota1: Switch = findViewById(R.id.switchCuota1)
        val switchCuota2: Switch = findViewById(R.id.switchCuota2)
        val switchCuota3: Switch = findViewById(R.id.switchCuota3)

        // Configurar cada switch
        configurarSwitch(switchCuota1)
        configurarSwitch(switchCuota2)
        configurarSwitch(switchCuota3)

        // Mostrar el total inicial
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

    // Acción al presionar la flecha
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
