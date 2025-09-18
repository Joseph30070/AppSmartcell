package com.example.smartcellapp

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDate
import java.util.Calendar

class CumpleanosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cumpleanos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Configurar toolbar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbarCumpleanos)
        setSupportActionBar(toolbar)

        // Activar flecha de regreso
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Datos de ejemplo mas adelanto con datos reales
        val cumpleanosPorMes = mapOf(
            "Enero" to listOf("Carlos - 10", "María - 25"),
            "Febrero" to listOf("José - 3", "Lucía - 19"),
            "Marzo" to listOf("Pedro - 7", "Ana - 22"),
            "Abril" to listOf("Luis - 14"),
            "Mayo" to listOf("Sofía - 30"),
            "Junio" to listOf("Andrés - 5"),
            "Julio" to listOf("Camila - 9"),
            "Agosto" to listOf("Raúl - 17"),
            "Septiembre" to listOf("Elena - 2", "Marco - 28"),
            "Octubre" to listOf("Diana - 12"),
            "Noviembre" to listOf("Hugo - 6"),
            "Diciembre" to listOf("Valeria - 24", "Miguel - 31")
        )

        // Obtener mes actual
        val mesActual = Calendar.getInstance().get(Calendar.MONTH) + 1  // 1 = Enero, 9 = Septiembre
        val meses = listOf(
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        )

        // Filtrar desde mes actual hasta diciembre
        val mesesFiltrados = meses.drop(mesActual - 1)

        // Referencia al contenedor en el XML
        val contenedor = findViewById<LinearLayout>(R.id.contenedorCumpleanos)

        // Generar dinámicamente los TextView
        for (mes in mesesFiltrados) {
            // Mes como título
            val txtMes = TextView(this).apply {
                text = "📅 $mes"
                textSize = 18f
                setPadding(0, 16, 0, 8)
            }
            contenedor.addView(txtMes)

            // Cumpleaños del mes
            cumpleanosPorMes[mes]?.forEach { cumple ->
                val txtCumple = TextView(this).apply {
                    text = "   🎂 $cumple"
                    textSize = 16f
                    setPadding(16, 4, 0, 4)
                }
                contenedor.addView(txtCumple)
            }
        }

    }
    // Manejar la acción de la flecha
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}


