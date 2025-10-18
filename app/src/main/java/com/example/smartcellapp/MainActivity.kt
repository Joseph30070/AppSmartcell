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
import androidx.core.content.ContextCompat

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
        val btnPaginaWeb = findViewById<MaterialButton>(R.id.btnPaginaWeb)
        val btnCursos = findViewById<MaterialButton>(R.id.btnCursos)
        val btnHorario = findViewById<MaterialButton>(R.id.btnHorario)
        val btnCuenta = findViewById<MaterialButton>(R.id.btnCuenta)
        val btnCumpleanos = findViewById<MaterialButton>(R.id.btnCumpleanos)
        val btnEnlaces = findViewById<MaterialButton>(R.id.btnEnlaces)
        val btnPagos = findViewById<MaterialButton>(R.id.btnPagos)
        val btnIdentificacion = findViewById<MaterialButton>(R.id.btnIdentificacion)

        val botones = listOf(
            btnPaginaWeb, btnCursos, btnHorario, btnCuenta,
            btnCumpleanos, btnEnlaces, btnPagos, btnIdentificacion
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

        // Botones con sus actividades
        btnPaginaWeb.setOnClickListener { startActivity(Intent(this, PaginaWebActivity::class.java)) }
        btnCursos.setOnClickListener { startActivity(Intent(this, CursosActivity::class.java)) }
        btnHorario.setOnClickListener { startActivity(Intent(this, HorariosActivity::class.java)) }
        btnCuenta.setOnClickListener { startActivity(Intent(this, CuentaActivity::class.java)) }
        btnCumpleanos.setOnClickListener { startActivity(Intent(this, CumpleanosActivity::class.java)) }
        btnEnlaces.setOnClickListener { startActivity(Intent(this, EnlacesActivity::class.java)) }
        btnPagos.setOnClickListener { startActivity(Intent(this, PagosActivity::class.java)) }
        btnIdentificacion.setOnClickListener { startActivity(Intent(this, IdentificacionActivity::class.java)) }
    }

    // Función para animar botón al presionar
    private fun animarBoton(boton: MaterialButton) {
        boton.setOnTouchListener { v, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100).start()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    v.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
                }
            }
            false
        }
    }

    // Función para efecto shimmer futurista sin opacar el texto
    private fun aplicarShimmerFuturista(boton: MaterialButton) {
        // Capa shimmer: solo el brillo (transparente al resto)
        val shimmer = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(0x00FFFFFF, 0x55FFFFFF, 0x00FFFFFF) // blanco semitransparente
        )
        shimmer.cornerRadius = 16f

        // Aplicar como foreground si la versión lo permite
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boton.foreground = shimmer
        } else {
            // Para versiones antiguas, podemos usar background, pero con transparencia máxima
            boton.background = shimmer
        }

        // Animar el shimmer
        val animator = ValueAnimator.ofFloat(-1f, 2f)
        animator.duration = 2000
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


