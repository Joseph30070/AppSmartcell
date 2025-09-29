package com.example.smartcellapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button   // 👈 Import necesario
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
        findViewById<Button>(R.id.btnFacebook).setOnClickListener {
            abrirEnNavegador("https://www.facebook.com/profile.php?id=61574682905135&locale=es_LA")
        }

        // Botón Instagram
        findViewById<Button>(R.id.btnInstagram).setOnClickListener {
            abrirEnNavegador("https://www.instagram.com/academiasmartcell")
        }

        // Botón TikTok
        findViewById<Button>(R.id.btnTiktok).setOnClickListener {
            abrirEnNavegador("https://www.tiktok.com/@academia.smartcell")
        }

        // Botón WhatsApp
        findViewById<Button>(R.id.btnWhatsapp).setOnClickListener {
            val phone = "51950262596" // 👈 número con código de país (ej: 51 para Perú)
            val message = "Hola, tengo una consulta"
            val url = "https://wa.me/$phone?text=${Uri.encode(message)}"

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

    }

    // Flecha de regreso
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}

