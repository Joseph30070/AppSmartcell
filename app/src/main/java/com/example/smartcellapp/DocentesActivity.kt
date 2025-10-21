package com.example.smartcellapp

import android.os.Bundle
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

        // Ajuste para sistema de barras (status/nav)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Al tocar el botón se despliega/oculta la información del profesor
        binding.btnVerInfoProfesor.setOnClickListener {
            toggleDetalles(binding.layoutInfoProfesor, binding.btnVerInfoProfesor)
        }
    }

    private fun toggleDetalles(detalles: View, boton: View) {
        val transition = AutoTransition().apply { duration = 300 }
        TransitionManager.beginDelayedTransition(binding.cardProfesor, transition)

        if (detalles.visibility == View.GONE) {
            detalles.visibility = View.VISIBLE
            if (boton is android.widget.Button) boton.text = "Ocultar Información"

            // Scroll automático para mostrar la info completa
            detalles.post {
                binding.scrollViewDocentes.smoothScrollTo(0, detalles.bottom)
            }

        } else {
            detalles.visibility = View.GONE
            if (boton is android.widget.Button) boton.text = "Ver Información"

            // Opcional: regresar al inicio de la tarjeta
            detalles.post {
                binding.scrollViewDocentes.smoothScrollTo(0, binding.cardProfesor.top)
            }
        }
    }
}




