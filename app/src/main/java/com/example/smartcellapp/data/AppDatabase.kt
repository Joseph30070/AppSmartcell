package com.example.smartcellapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.smartcellapp.data.dao.AlumnoDao
import com.example.smartcellapp.data.entity.Alumno
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Alumno::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun alumnoDao(): AlumnoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "smartcell_db"
                ).build()

                // Cargar datos iniciales solo una vez
                CoroutineScope(Dispatchers.IO).launch {
                    val dao = instance.alumnoDao()

                    val alumnos = listOf(
                        Alumno(
                            idAlumno = "0012345",
                            nombre = "Luis",
                            apellido = "Gomez",
                            dni = "12345678",
                            correo = "luis.gomez@gmail.pe",
                            celular = "987654321",
                            cursoMatriculado = "Reparación de Celulares",
                            fechaNacimiento = "2004-02-10"
                        ),
                        Alumno(
                            idAlumno = "0016789",
                            nombre = "Maria",
                            apellido = "Rosas",
                            dni = "87654321",
                            correo = "maria.rosas@gmail.pe",
                            celular = "912345678",
                            cursoMatriculado = "Robótica",
                            fechaNacimiento = "2003-08-25"
                        )
                    )

                    alumnos.forEach { dao.insertarAlumno(it) }
                }

                INSTANCE = instance
                instance
            }
        }
    }
}

