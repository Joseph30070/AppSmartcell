package com.example.smartcellapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.util.Calendar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CursosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cursos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar toolbar con botón de retroceso
        val toolbar = findViewById<Toolbar>(R.id.toolbarCursos)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // flecha atrás
        supportActionBar?.title = "Mis Cursos - ${Calendar.getInstance().get(Calendar.YEAR)}"

        // Lista de ejemplo de cursos
        val cursos = listOf(
            "🤖 Robótica Avanzada",
            "🔌 Electrónica Digital",
            "💻 Reparación de Laptops",
            "🖥️ Reparación de PCs"
        )

        // Adaptador para mostrar los cursos
        val listView = findViewById<ListView>(R.id.listCursos)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, cursos)
        listView.adapter = adapter

        // Evento al hacer clic en un curso
        listView.setOnItemClickListener { _, _, position, _ ->
            val cursoSeleccionado = cursos[position]
            Toast.makeText(this, "Abriste $cursoSeleccionado", Toast.LENGTH_SHORT).show()
        }
    }

    // Hacer que la flecha funcione
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
