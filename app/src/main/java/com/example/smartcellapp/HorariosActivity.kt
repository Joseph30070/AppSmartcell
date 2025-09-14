package com.example.smartcellapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import java.text.SimpleDateFormat
import java.util.*

class HorariosActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var calendarView: CalendarView

    // Lista de cursos (nombre, fecha, hora) por ahora predefinidos luego base de datos
    private val eventos = listOf(
        Triple("Robótica Avanzada", "11/09/2025", "9:00 am - 12:00 pm"),
        Triple("Electrónica Digital", "14/09/2025", "3:00 pm - 6:00 pm"),
        Triple("Reparación de PCs", "20/09/2025", "10:00 am - 1:00 pm")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_horario)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Toolbar con retroceso
        val toolbar = findViewById<Toolbar>(R.id.toolbarHorarios)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Referencia al calendario y lista
        calendarView = findViewById(R.id.calendarView)
        listView = findViewById(R.id.listEventos)

        // Adaptador inicial con todos los cursos
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            eventos.map { "${it.first}\n📅 ${it.second}  ⏰ ${it.third}" }
        )
        listView.adapter = adapter

        // Mostrar puntitos en el calendario
        val eventosCalendario = mutableListOf<EventDay>()
        val clase1 = Calendar.getInstance().apply { set(2025, 8, 11) } // Septiembre = 8
        val clase2 = Calendar.getInstance().apply { set(2025, 8, 14) }
        val clase3 = Calendar.getInstance().apply { set(2025, 8, 20) }

        eventosCalendario.add(EventDay(clase1, R.drawable.ic_circle_blue))
        eventosCalendario.add(EventDay(clase2, R.drawable.ic_circle_blue))
        eventosCalendario.add(EventDay(clase3, R.drawable.ic_circle_blue))
        calendarView.setEvents(eventosCalendario)

        // Acción al hacer clic en una fecha
        calendarView.setOnDayClickListener(object :
            com.applandeo.materialcalendarview.listeners.OnDayClickListener {
            override fun onDayClick(eventDay: EventDay) {
                val clickedDay = eventDay.calendar.time
                val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val fechaSeleccionada = formato.format(clickedDay)

                val cursosDelDia = eventos.filter { it.second == fechaSeleccionada }

                adapter.clear()
                if (cursosDelDia.isNotEmpty()) {
                    adapter.addAll(cursosDelDia.map {
                        "${it.first}\n📅 ${it.second}  ⏰ ${it.third}"
                    })
                } else {
                    adapter.add("❌ No hay clases programadas para $fechaSeleccionada")
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}






