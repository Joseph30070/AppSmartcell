package com.example.smartcellapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EnlacesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_enlaces)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar toolbar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbarEnlaces)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Función para abrir siempre en navegador
        fun abrirEnNavegador(url: String) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                // 👉 Esto evita que lo intente abrir la app oficial
                addCategory(Intent.CATEGORY_BROWSABLE)
                setPackage("com.android.chrome") // Forzar Chrome (si está instalado)
            }

            // Si el usuario no tiene Chrome, abrir en cualquier navegador
            if (intent.resolveActivity(packageManager) == null) {
                val intentGenerico = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intentGenerico)
            } else {
                startActivity(intent)
            }
        }

        // Botón Facebook
        findViewById<android.widget.Button>(R.id.btnFacebook).setOnClickListener {
            abrirEnNavegador("https://www.facebook.com/profile.php?id=61574682905135&locale=es_LA") //link de facebook
        }

        // Botón Instagram
        findViewById<android.widget.Button>(R.id.btnInstagram).setOnClickListener {
            abrirEnNavegador("https://www.instagram.com/academiasmartcell") //link de instagram
        }

        // Botón TikTok
        findViewById<android.widget.Button>(R.id.btnTiktok).setOnClickListener {
            abrirEnNavegador("https://www.tiktok.com/@academia.smartcell") // link de tiktok
        }
    }

    // Flecha de regreso
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
