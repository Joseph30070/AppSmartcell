package com.example.smartcellapp

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CursosActivity : AppCompatActivity() {

    private val cursos = listOf(
        "🤖 Robótica" to """
            Aprende a programar robots, usar sensores, motores y construir proyectos de automatización.
            - Duración: 4 meses
            - Nivel: Intermedio
            - Modalidad: Presencial / Virtual
        """.trimIndent(),
        "📄 Ofimática" to """
            Domina herramientas como Word, Excel, PowerPoint y gestión de documentos digitales.
            - Duración: 3 meses
            - Nivel: Básico
            - Modalidad: Presencial / Virtual
        """.trimIndent(),
        "⚡ Electrónica" to """
            Aprende sobre circuitos, componentes, multímetros y fundamentos de electrónica práctica.
            - Duración: 4 meses
            - Nivel: Básico - Intermedio
            - Modalidad: Presencial
        """.trimIndent(),
        "📱 Reparación de Celulares" to """
            Diagnóstico, mantenimiento y reparación de equipos móviles de distintas gamas.
            - Duración: 4 meses
            - Nivel: Intermedio
            - Modalidad: Presencial
        """.trimIndent(),
        "💻 Reparación de PC y Laptop" to """
            Mantenimiento, armado, instalación de software y solución de fallas en equipos de escritorio y portátiles.
            - Duración: 4 meses
            - Nivel: Básico - Intermedio
            - Modalidad: Presencial / Virtual
        """.trimIndent()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cursos)

        // Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbarCursos)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Mis Cursos 2025"
        toolbar.setNavigationOnClickListener { finish() }

        // Ajuste de barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val contenedor = findViewById<LinearLayout>(R.id.layoutCursos)
        val inflater = LayoutInflater.from(this)

        // Crear dinámicamente una tarjeta por curso
        for ((nombre, descripcion) in cursos) {
            val card = inflater.inflate(R.layout.item_curso_card, contenedor, false)
            val cardView = card.findViewById<CardView>(R.id.cardCurso)
            val titulo = card.findViewById<TextView>(R.id.txtTituloCurso)
            val detalles = card.findViewById<TextView>(R.id.txtDetallesCurso)
            val boton = card.findViewById<Button>(R.id.btnVerDetalles)

            titulo.text = nombre
            detalles.text = descripcion

            boton.setOnClickListener {
                val transition = AutoTransition().apply { duration = 300 }
                TransitionManager.beginDelayedTransition(cardView, transition)
                if (detalles.visibility == View.GONE) {
                    detalles.visibility = View.VISIBLE
                    boton.text = "Ocultar Información"
                } else {
                    detalles.visibility = View.GONE
                    boton.text = "Ver Información"
                }
            }

            contenedor.addView(card)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}


