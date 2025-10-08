package com.example.smartcellapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
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

        // Botón Página Web
        btnPaginaWeb.setOnClickListener {
            val intent = Intent(this, PaginaWebActivity::class.java)
            startActivity(intent)
        }

        // Botón Cursos
        btnCursos.setOnClickListener {
            val intent = Intent(this, CursosActivity::class.java)
            startActivity(intent)
        }

        // Botón Horarios
        btnHorario.setOnClickListener {
            val intent = Intent(this, HorariosActivity::class.java)
            startActivity(intent)
        }

        // Botón Cuenta
        btnCuenta.setOnClickListener {
            val intent = Intent(this, CuentaActivity::class.java)
            startActivity(intent)
        }

        // Botón Cumpleaños
        btnCumpleanos.setOnClickListener {
            val intent = Intent(this, CumpleanosActivity::class.java)
            startActivity(intent)
        }

        // Botón Enlaces
        btnEnlaces.setOnClickListener {
            val intent = Intent(this, EnlacesActivity::class.java)
            startActivity(intent)
        }

        // Botón Pagos
        btnPagos.setOnClickListener {
            val intent = Intent(this, PagosActivity::class.java)
            startActivity(intent)
        }

        // Botón Identificación
        btnIdentificacion.setOnClickListener {
            val intent = Intent(this, IdentificacionActivity::class.java)
            startActivity(intent)
        }

        // ============================================
        // 🔹 NUEVO: Menú de tres puntos para cerrar sesión
        // ============================================
        val btnMenu = findViewById<ImageView>(R.id.btnMenu)
        btnMenu?.setOnClickListener { view ->
            val popupMenu = PopupMenu(this, view)
            popupMenu.menu.add("Cerrar sesión")

            popupMenu.setOnMenuItemClickListener { item ->
                if (item.title == "Cerrar sesión") {
                    // Regresa al login
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish() // Cierra la actividad actual
                    true
                } else {
                    false
                }
            }
            popupMenu.show()
        }
    }
}


