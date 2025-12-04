package com.example.smartcellapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CursosActivity : AppCompatActivity() {

    private data class Curso(
        val nombre: String,
        val descripcion: String,
        val telegram: String
    )

    private val cursos = listOf(
        Curso(
            "🤖 Robótica",
            """
            Aprende a programar robots, usar sensores, motores y construir proyectos de automatización.
            - Duración: 4 meses
            - Nivel: Intermedio
            - Modalidad: Presencial / Virtual
            - Precio: S/180 por mes
            - Pagos: 4 mensualidades
            """.trimIndent(),
            "https://t.me/roboticaBeatCell"
        ),
        Curso(
            "📄 Ofimática",
            """
            Domina herramientas como Word, Excel, PowerPoint y gestión de documentos digitales.
            - Duración: 3 meses
            - Nivel: Básico
            - Modalidad: Presencial / Virtual
            - Precio: S/150 por mes
            - Pagos: 3 mensualidades
            """.trimIndent(),
            "https://t.me/ofimaticaBeatCell"
        ),
        Curso(
            "⚡ Electrónica",
            """
            Aprende sobre circuitos, componentes, multímetros y fundamentos de electrónica práctica.
            - Duración: 4 meses
            - Nivel: Básico - Intermedio
            - Modalidad: Presencial
            - Precio: S/170 por mes
            - Pagos: 4 mensualidades
            """.trimIndent(),
            "https://t.me/electronicaBeatCell"
        ),
        Curso(
            "📱 Reparación de Celulares",
            """
            Diagnóstico, mantenimiento y reparación de equipos móviles de distintas gamas.
            - Duración: 4 meses
            - Nivel: Intermedio
            - Modalidad: Presencial
            - Precio: S/200 por mes
            - Pagos: 4 mensualidades
            """.trimIndent(),
            "https://t.me/celularesBeatCell"
        ),
        Curso(
            "💻 Reparación de PC y Laptop",
            """
            Mantenimiento, armado, instalación de software y solución de fallas en equipos de escritorio y portátiles.
            - Duración: 4 meses
            - Nivel: Básico - Intermedio
            - Modalidad: Presencial / Virtual
            - Precio: S/190 por mes
            - Pagos: 4 mensualidades
            """.trimIndent(),
            "https://t.me/pcsBeatCell"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cursos)

        val toolbar = findViewById<Toolbar>(R.id.toolbarCursos)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "CURSOS"
        toolbar.setNavigationOnClickListener { finish() }

        val btnAtras = findViewById<FloatingActionButton>(R.id.btnAtras)
        btnAtras.setOnClickListener { finish() }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val contenedor = findViewById<LinearLayout>(R.id.layoutCursos)
        val inflater = LayoutInflater.from(this)

        for (curso in cursos) {

            val card = inflater.inflate(R.layout.item_curso_card, contenedor, false)

            val cardView = card.findViewById<CardView>(R.id.cardCurso)
            val titulo = card.findViewById<TextView>(R.id.txtTituloCurso)
            val detalles = card.findViewById<TextView>(R.id.txtDetallesCurso)
            val masInfo = card.findViewById<TextView>(R.id.txtMasInformacion)
            val imgTelegram = card.findViewById<ImageView>(R.id.ivTelegram)

            titulo.text = curso.nombre
            detalles.text = curso.descripcion

            imgTelegram.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(curso.telegram))
                startActivity(intent)
            }

            cardView.setOnClickListener {
                val transition = AutoTransition().apply { duration = 300 }
                TransitionManager.beginDelayedTransition(cardView, transition)
                if (detalles.visibility == View.GONE) {
                    detalles.visibility = View.VISIBLE
                    masInfo.text = "Menos información ▲"
                } else {
                    detalles.visibility = View.GONE
                    masInfo.text = "Más información ▼"
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




