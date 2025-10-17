package com.example.smartcellapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.smartcellapp.data.AppDatabase
import kotlinx.coroutines.launch
import com.example.smartcellapp.data.dao.CursoDao

class CuentaActivity : AppCompatActivity() {

    private lateinit var txtNombre: TextView
    private lateinit var txtCorreo: TextView
    private lateinit var txtTelefono: TextView
    private lateinit var txtCodigo: TextView
    private lateinit var txtCarrera: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cuenta)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbarCuenta)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Referencias a los TextView
        txtNombre = findViewById(R.id.txtNombre)
        txtCorreo = findViewById(R.id.txtCorreo)
        txtTelefono = findViewById(R.id.txtTelefono)
        txtCodigo = findViewById(R.id.txtCodigo)
        txtCarrera = findViewById(R.id.txtCarrera)

        // Obtener el ID del alumno desde el intent
        val idAlumno = intent.getStringExtra("idAlumno")

        if (idAlumno != null) {
            lifecycleScope.launch {
                val db = AppDatabase.getDatabase(applicationContext)
                val alumnoDao = db.alumnoDao()
                val cursoDao = db.cursoDao()

                val alumno = alumnoDao.buscarPorId(idAlumno)

                if (alumno != null) {
                    // Obtener el curso del alumno
                    val curso = alumno.idCurso?.let { idCurso ->
                        cursoDao.obtenerCursoPorId(idCurso)
                    }

                    // Mostrar los datos en pantalla
                    txtNombre.text = "👤 Nombre: ${alumno.nombre} ${alumno.apellido}"
                    txtCorreo.text = "📧 Correo: ${alumno.correo}"
                    txtTelefono.text = "📱 Teléfono: ${alumno.celular}"
                    txtCodigo.text = "🎓 Código Alumno: ${alumno.idAlumno}"
                    txtCarrera.text = "📚 Curso: ${curso?.nombreCurso ?: "Sin asignar"}"
                } else {
                    txtNombre.text = "Alumno no encontrado."
                }
            }
        } else {
            txtNombre.text = "Error: no se recibió el ID del alumno."
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}



