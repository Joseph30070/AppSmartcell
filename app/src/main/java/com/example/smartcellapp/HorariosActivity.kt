package com.example.smartcellapp

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

data class CursoHorario(
    val nombre: String,
    val dias: String,
    val horario: String,
    val descripcion: String
)

class HorariosActivity : AppCompatActivity() {

    private val cursos = listOf(
        CursoHorario("🤖 Robótica", "Lun, Mié, Vie", "9:00 - 11:00", "¡Construye robots y diviértete programando!"),
        CursoHorario("📄 Ofimática", "Mar, Jue", "14:00 - 16:00", "Aprende Excel y Word como un pro 💻"),
        CursoHorario("⚡ Electrónica", "Lun-Vie", "11:00 - 13:00", "Circuitos, LEDs y chispa ⚡"),
        CursoHorario("📱 Reparación de Celulares", "Mar, Jue", "10:00 - 12:00", "Arregla móviles sin morir en el intento 📱"),
        CursoHorario("💻 Reparación de PC y Laptop", "Lun, Mié, Vie", "15:00 - 17:00", "Domina tu PC y salva computadoras 💻")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_horario)

        // Toolbar azul con título y flecha
        val toolbar = findViewById<Toolbar>(R.id.toolbarHorarios)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Horario de Clases"
        toolbar.setNavigationOnClickListener { finish() }

        // Ajuste de barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Contenedor donde se añadirán las tarjetas
        val contenedor = findViewById<LinearLayout>(R.id.layoutHorarios)
        val inflater = LayoutInflater.from(this)

        // Crear tarjetas por curso con acordeón
        for (curso in cursos) {
            val card = inflater.inflate(R.layout.item_horario_card, contenedor, false)
            val cardView = card.findViewById<CardView>(R.id.cardHorario)
            val nombre = card.findViewById<TextView>(R.id.txtTituloHorario)
            val descripcion = card.findViewById<TextView>(R.id.txtDescripcionHorario)
            val horario = card.findViewById<TextView>(R.id.txtHoraHorario)

            nombre.text = curso.nombre
            horario.text = "${curso.dias} | ${curso.horario}"
            descripcion.text = curso.descripcion

            // Inicialmente la descripción oculta
            descripcion.visibility = TextView.GONE

            // Toggle acordeón
            cardView.setOnClickListener {
                val transition = AutoTransition().apply { duration = 300 }
                TransitionManager.beginDelayedTransition(cardView, transition)
                descripcion.visibility = if (descripcion.visibility == TextView.GONE) TextView.VISIBLE else TextView.GONE
            }

            // Añadir separación entre tarjetas
            val params = cardView.layoutParams as LinearLayout.LayoutParams
            params.setMargins(0, 12, 0, 12)
            cardView.layoutParams = params

            contenedor.addView(card)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}









