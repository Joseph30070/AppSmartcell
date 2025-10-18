package com.example.smartcellapp

import android.os.Bundle
import android.widget.Button
import android.content.Intent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Forzar tema claro en toda la app
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencias a los botones
        val btnPaginaWeb = findViewById<Button>(R.id.btnPaginaWeb)
        val btnCursos = findViewById<Button>(R.id.btnCursos)
        val btnHorario = findViewById<Button>(R.id.btnHorario)
        val btnCuenta = findViewById<Button>(R.id.btnCuenta)
        val btnCumpleanos = findViewById<Button>(R.id.btnCumpleanos)
        val btnEnlaces = findViewById<Button>(R.id.btnEnlaces)
        val btnPagos = findViewById<Button>(R.id.btnPagos)
        val btnIdentificacion = findViewById<Button>(R.id.btnIdentificacion)

        // Botones con sus actividades
        btnPaginaWeb.setOnClickListener {
            startActivity(Intent(this, PaginaWebActivity::class.java))
        }

        btnCursos.setOnClickListener {
            startActivity(Intent(this, CursosActivity::class.java))
        }

        btnHorario.setOnClickListener {
            startActivity(Intent(this, HorariosActivity::class.java))
        }

        btnCuenta.setOnClickListener {
            startActivity(Intent(this, CuentaActivity::class.java))
        }

        btnCumpleanos.setOnClickListener {
            startActivity(Intent(this, CumpleanosActivity::class.java))
        }

        btnEnlaces.setOnClickListener {
            startActivity(Intent(this, EnlacesActivity::class.java))
        }

        btnPagos.setOnClickListener {
            startActivity(Intent(this, PagosActivity::class.java))
        }

        btnIdentificacion.setOnClickListener {
            startActivity(Intent(this, IdentificacionActivity::class.java))
        }
    }
}




