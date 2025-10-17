package com.example.smartcellapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.content.Intent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.smartcellapp.data.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var idAlumno: Int = -1
    private lateinit var txtBienvenida: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        // Forzar tema claro
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ajuste para compatibilidad de bordes
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recuperar el idAlumno enviado desde LoginActivity
        idAlumno = intent.getIntExtra("idAlumno", -1)

        // Referencia al texto de bienvenida
        txtBienvenida = findViewById(R.id.txtBienvenida)

        // Mostrar el nombre del alumno logueado
        if (idAlumno != -1) {
            CoroutineScope(Dispatchers.IO).launch {
                val alumno = AppDatabase.getDatabase(this@MainActivity)
                    .alumnoDao()
                    .buscarPorId(idAlumno.toString())

                runOnUiThread {
                    if (alumno != null) {
                        txtBienvenida.text = "Bienvenido, ${alumno.nombre}"
                    } else {
                        txtBienvenida.text = "Bienvenido"
                    }
                }
            }
        } else {
            txtBienvenida.text = "Bienvenido"
        }

        // 🔹 Botones principales
        val btnPaginaWeb = findViewById<Button>(R.id.btnPaginaWeb)
        val btnCursos = findViewById<Button>(R.id.btnCursos)
        val btnHorario = findViewById<Button>(R.id.btnHorario)
        val btnCuenta = findViewById<Button>(R.id.btnCuenta)
        val btnCumpleanos = findViewById<Button>(R.id.btnCumpleanos)
        val btnEnlaces = findViewById<Button>(R.id.btnEnlaces)
        val btnPagos = findViewById<Button>(R.id.btnPagos)
        val btnIdentificacion = findViewById<Button>(R.id.btnIdentificacion)

        // 🔹 Función auxiliar para pasar idAlumno a otras actividades
        fun irAActividad(clase: Class<*>) {
            val intent = Intent(this, clase)
            intent.putExtra("idAlumno", idAlumno)
            startActivity(intent)
        }

        // 🔹 Eventos de los botones
        btnPaginaWeb.setOnClickListener { irAActividad(PaginaWebActivity::class.java) }
        btnCursos.setOnClickListener { irAActividad(CursosActivity::class.java) }
        btnHorario.setOnClickListener { irAActividad(HorariosActivity::class.java) }
        btnCuenta.setOnClickListener { irAActividad(CuentaActivity::class.java) }
        btnCumpleanos.setOnClickListener { irAActividad(CumpleanosActivity::class.java) }
        btnEnlaces.setOnClickListener { irAActividad(EnlacesActivity::class.java) }
        btnPagos.setOnClickListener { irAActividad(PagosActivity::class.java) }
        btnIdentificacion.setOnClickListener { irAActividad(IdentificacionActivity::class.java) }

        // 🔹 Menú de cerrar sesión
        val btnMenu = findViewById<ImageView>(R.id.btnMenu)
        btnMenu?.setOnClickListener { view ->
            val popupMenu = PopupMenu(this, view)
            popupMenu.menu.add("Cerrar sesión")

            popupMenu.setOnMenuItemClickListener { item ->
                if (item.title == "Cerrar sesión") {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                } else false
            }
            popupMenu.show()
        }
    }
}




