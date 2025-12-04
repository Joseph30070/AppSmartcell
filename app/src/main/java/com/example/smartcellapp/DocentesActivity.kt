package com.example.smartcellapp

import android.os.Bundle
import android.view.ViewGroup
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.smartcellapp.databinding.ActivityDocentesBinding

class DocentesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDocentesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDocentesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar Toolbar con título y flecha de retroceso
        setSupportActionBar(binding.toolbarDocentes)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Nuestros Docentes"
        binding.toolbarDocentes.setNavigationOnClickListener { finish() }

        // Ajuste para barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ---- PROFESOR 1 ----
        binding.btnVerInfoProfesor1.setOnClickListener {
            toggleDetalles(
                binding.layoutInfoProfesor1,
                binding.btnVerInfoProfesor1,
                binding.cardProfesor1
            )
        }

        // ---- PROFESOR 2 ----
        binding.btnVerInfoProfesor2.setOnClickListener {
            toggleDetalles(
                binding.layoutInfoProfesor2,
                binding.btnVerInfoProfesor2,
                binding.cardProfesor2
            )
        }

        // ---- PROFESOR 3 ----
        binding.btnVerInfoProfesor3.setOnClickListener {
            toggleDetalles(
                binding.layoutInfoProfesor3,
                binding.btnVerInfoProfesor3,
                binding.cardProfesor3
            )
        }
    }

    /**
     * Función para mostrar/ocultar con animación sin romper nada
     */
    private fun toggleDetalles(detalles: View, boton: View, card: View) {
        val transition = AutoTransition().apply { duration = 300 }
        TransitionManager.beginDelayedTransition(card as ViewGroup, transition)

        if (detalles.visibility == View.GONE) {
            detalles.visibility = View.VISIBLE
            if (boton is android.widget.Button) boton.text = "Ocultar Información"

            detalles.post {
                binding.scrollViewDocentes.smoothScrollTo(0, detalles.bottom)
            }

        } else {
            detalles.visibility = View.GONE
            if (boton is android.widget.Button) boton.text = "Ver Información"

            detalles.post {
                binding.scrollViewDocentes.smoothScrollTo(0, card.top)
            }
        }
    }
}





