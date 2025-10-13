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
import com.example.smartcellapp.data.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

        val edtIdAlumno = findViewById<EditText>(R.id.edtIdAlumno)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        val db = AppDatabase.getDatabase(this)
        val alumnoDao = db.alumnoDao()

        btnLogin.setOnClickListener {
            val idIngresado = edtIdAlumno.text.toString().trim()
            val fechaIngresada = edtPassword.text.toString().trim()

            if (idIngresado.isEmpty() || fechaIngresada.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 🧠 Convertir fecha de ddMMyyyy → yyyy-MM-dd
            val fechaFormateada = try {
                val dia = fechaIngresada.substring(0, 2)
                val mes = fechaIngresada.substring(2, 4)
                val anio = fechaIngresada.substring(4, 8)
                "$anio-$mes-$dia"
            } catch (e: Exception) {
                null
            }

            if (fechaFormateada == null) {
                Toast.makeText(this, "Formato de fecha inválido. Usa ddMMyyyy (ej: 10022004)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 🔍 Verificar en la base de datos
            CoroutineScope(Dispatchers.IO).launch {
                val alumno = alumnoDao.verificarLogin(idIngresado, fechaFormateada)

                runOnUiThread {
                    if (alumno != null) {
                        Toast.makeText(
                            this@LoginActivity,
                            "✅ Bienvenido ${alumno.nombre} ${alumno.apellido}",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "❌ ID o fecha incorrectos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}


