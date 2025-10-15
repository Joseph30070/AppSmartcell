package com.example.smartcellapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.smartcellapp.data.dao.AlumnoDao
import com.example.smartcellapp.data.entity.Alumno
import com.example.smartcellapp.data.dao.CursoDao
import com.example.smartcellapp.data.dao.MatriculaDao
import com.example.smartcellapp.data.entity.Curso
import com.example.smartcellapp.data.entity.Matricula
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Alumno::class, Curso::class, Matricula::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun alumnoDao(): AlumnoDao
    abstract fun cursoDao(): CursoDao
    abstract fun matriculaDao(): MatriculaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "smartcell_db"
                )
                    .fallbackToDestructiveMigration() // ✅ Soluciona el error de migración
                    .build()

                CoroutineScope(Dispatchers.IO).launch {
                    val alumnoDao = instance.alumnoDao()
                    val cursoDao = instance.cursoDao()

                    val cursos = listOf(
                        Curso(idCurso = 1, nombreCurso = "Reparación de Celulares"),
                        Curso(idCurso = 2, nombreCurso = "Robótica"),
                        Curso(idCurso = 3, nombreCurso = "Reparación de Laptops")
                    )
                    cursos.forEach { cursoDao.insertarCurso(it) }

                    val alumnos = listOf(
                        Alumno(
                            idAlumno = "0012345",
                            nombre = "Luis",
                            apellido = "Gomez",
                            dni = "12345678",
                            correo = "luis.gomez@gmail.pe",
                            celular = "987654321",
                            idCurso = 1,
                            fechaNacimiento = "2004-02-10"
                        ),
                        Alumno(
                            idAlumno = "0016789",
                            nombre = "Maria",
                            apellido = "Rosas",
                            dni = "87654321",
                            correo = "maria.rosas@gmail.pe",
                            celular = "912345678",
                            idCurso = 2,
                            fechaNacimiento = "2003-08-25"
                        )
                    )
                    alumnos.forEach { alumnoDao.insertarAlumno(it) }
                }

                INSTANCE = instance
                instance
            }
        }
    }
}



