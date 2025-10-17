package com.example.smartcellapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        // Forzar siempre tema claro
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencias a los elementos del XML
        val edtIdAlumno = findViewById<EditText>(R.id.edtIdAlumno)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        // Datos de ejemplo
        val idCorrecto = "1234567"
        val passwordCorrecto = "30072003"

        btnLogin.setOnClickListener {
            val idIngresado = edtIdAlumno.text.toString().trim()
            val passwordIngresada = edtPassword.text.toString().trim()

            if (idIngresado == idCorrecto && passwordIngresada == passwordCorrecto) {
                Toast.makeText(this, "✅ Bienvenido alumno $idIngresado", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // cierra el login para que no regrese
            } else {
                Toast.makeText(this, "❌ ID o contraseña incorrectos", Toast.LENGTH_SHORT).show()

            }
        }
    }
}
