package com.example.smartcellapp

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.smartcellapp.databinding.ActivityNosotrosBinding

class NosotrosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNosotrosBinding
    private var paso = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNosotrosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar Toolbar
        setSupportActionBar(binding.toolbarNosotros)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Sobre Nosotros"
        binding.toolbarNosotros.setNavigationOnClickListener { finish() }

        // Acción del botón "Ver más"
        binding.btnVerMas.setOnClickListener {
            when (paso) {
                0 -> {
                    mostrarConTransicion(binding.layoutVision)
                    paso++
                }
                1 -> {
                    mostrarConTransicion(binding.layoutOfrecemos)
                    paso++
                    binding.btnVerMas.text = "Ver menos ↑"
                }
                else -> {
                    ocultarTodo()
                    paso = 0
                    binding.btnVerMas.text = "Ver más →"
                }
            }
        }
    }

    // Mostrar sección con transición suave y desplazamiento automático
    private fun mostrarConTransicion(vista: View) {
        TransitionManager.beginDelayedTransition(binding.layoutPrincipal, AutoTransition())
        vista.visibility = View.VISIBLE

        // Fade + slide del texto
        val fadeSlide = AnimationUtils.loadAnimation(this, R.anim.fade_slide_in)
        vista.startAnimation(fadeSlide)

        // 🔹 Pequeña animación de rebote visual del botón (sin moverlo manualmente)
        val bounce = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        binding.btnVerMas.startAnimation(bounce)

        // 🔹 Desplazamiento suave hasta el nuevo contenido
        vista.postDelayed({
            binding.scrollView.smoothScrollTo(0, vista.bottom)
        }, 300)
    }

    private fun ocultarTodo() {
        TransitionManager.beginDelayedTransition(binding.layoutPrincipal, AutoTransition())

        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        binding.layoutOfrecemos.startAnimation(slideUp)
        binding.layoutVision.startAnimation(slideUp)

        binding.layoutOfrecemos.visibility = View.GONE
        binding.layoutVision.visibility = View.GONE

        // Volver al inicio del scroll
        binding.scrollView.postDelayed({
            binding.scrollView.smoothScrollTo(0, 0)
        }, 300)

        // Fade visual del botón al volver
        val fade = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        binding.btnVerMas.startAnimation(fade)
    }
}

















