package com.example.smartcellapp

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.*

class IdentificacionActivity : AppCompatActivity() {

    private lateinit var txtReloj: TextView
    private val handler = Handler(Looper.getMainLooper())
    private val formatoHora = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

    private val runnable = object : Runnable {
        override fun run() {
            val horaActual = formatoHora.format(Date())
            txtReloj.text = horaActual
            handler.postDelayed(this, 1000) // refresca cada 1 segundo
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_identificacion)

        // ✅ Bloqueamos esta pantalla en horizontal
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ✅ Inicializamos el TextView del reloj
        txtReloj = findViewById(R.id.txtReloj)

        // ✅ Inicia la actualización del reloj
        handler.post(runnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable) // evita fugas de memoria
    }
}
