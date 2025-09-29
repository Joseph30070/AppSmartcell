package com.example.smartcellapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
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
            val descripcion = when (cursoSeleccionado) {
                "🤖 Robótica Avanzada" -> """
                    En este curso aprenderás a programar robots, usar sensores, motores y realizar proyectos de automatización.
                    - Duración: 4 meses
                    - Nivel: Intermedio
                    - Modalidad: Presencial / Virtual
                """.trimIndent()
                "🔌 Electrónica Digital" -> """
                    Aprende circuitos lógicos, compuertas, microcontroladores y fundamentos de electrónica aplicada.
                    - Duración: 4 meses
                    - Nivel: Básico - Intermedio
                    - Modalidad: Presencial
                """.trimIndent()
                "💻 Reparación de Laptops" -> """
                    Curso orientado al diagnóstico, mantenimiento y reparación de laptops modernas.
                    - Duración: 4 meses
                    - Nivel: Básico
                    - Modalidad: Presencial / Virtual
                """.trimIndent()
                "🖥️ Reparación de PCs" -> """
                    Domina el armado, mantenimiento, instalación de software y solución de problemas en computadoras de escritorio.
                    - Duración: 4 meses
                    - Nivel: Básico - Intermedio
                    - Modalidad: Presencial
                """.trimIndent()
                else -> "Información no disponible."
            }

            // Mostrar descripción en un AlertDialog
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle(cursoSeleccionado)
                .setMessage(descripcion)
                .setPositiveButton("OK", null)
                .show()
        }
    }

    // Hacer que la flecha funcione
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}

