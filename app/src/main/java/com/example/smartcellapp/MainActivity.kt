package com.example.smartcellapp

import android.animation.ValueAnimator
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.animation.LinearInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import android.content.Intent

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

        // Referencias actualizadas a los botones
        val btnPaginaWeb = findViewById<MaterialButton>(R.id.btnPaginaWeb)
        val btnCursos = findViewById<MaterialButton>(R.id.btnCursos)
        val btnHorario = findViewById<MaterialButton>(R.id.btnHorario)
        val btnDocentes = findViewById<MaterialButton>(R.id.btnDocentes)
        val btnSobreNosotros = findViewById<MaterialButton>(R.id.btnSobreNosotros)
        val btnEnlaces = findViewById<MaterialButton>(R.id.btnEnlaces)


        val botones = listOf(
            btnPaginaWeb, btnCursos, btnHorario,
            btnDocentes, btnSobreNosotros, btnEnlaces
        )

        // Animación de entrada (fade + slide) en cascada
        botones.forEachIndexed { index, boton ->
            boton.alpha = 0f
            boton.translationY = 100f
            boton.animate()
                .alpha(1f)
                .translationY(0f)
                .setStartDelay((index * 100).toLong())
                .setDuration(500)
                .start()
        }

        // Efecto de rebote al presionar
        botones.forEach { animarBoton(it) }

        // Aplicar efecto shimmer futurista
        botones.forEach { aplicarShimmerFuturista(it) }

        // Acciones de los botones
        btnPaginaWeb.setOnClickListener { startActivity(Intent(this, PaginaWebActivity::class.java)) }
        btnCursos.setOnClickListener { startActivity(Intent(this, CursosActivity::class.java)) }
        btnHorario.setOnClickListener { startActivity(Intent(this, HorariosActivity::class.java)) }
        btnDocentes.setOnClickListener { startActivity(Intent(this, DocentesActivity::class.java)) }
        btnSobreNosotros.setOnClickListener { startActivity(Intent(this, NosotrosActivity::class.java)) }
        btnEnlaces.setOnClickListener { startActivity(Intent(this, EnlacesActivity::class.java)) }

    }

    // Efecto de rebote al presionar
    private fun animarBoton(boton: MaterialButton) {
        boton.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> v.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100).start()
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> v.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
            }
            false
        }
    }

    // Efecto shimmer futurista sin alterar el color base
    private fun aplicarShimmerFuturista(boton: MaterialButton) {
        val shimmer = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(0x00FFFFFF, 0x33FFFFFF, 0x00FFFFFF)
        )
        shimmer.cornerRadius = 16f

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boton.foreground = shimmer
        }

        val animator = ValueAnimator.ofFloat(-1f, 2f)
        animator.duration = 2500
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            shimmer.setGradientCenter(value, 0.5f)
            boton.invalidate()
        }
        animator.start()
    }
}




