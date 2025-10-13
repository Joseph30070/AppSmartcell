package com.example.smartcellapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.smartcellapp.data.entity.Alumno

@Dao
interface AlumnoDao {

    // Insertar un alumno
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarAlumno(alumno: Alumno)

    // Insertar varios alumnos (para AppDatabase)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarAlumnos(alumnos: List<Alumno>)

    // Consultar todos los alumnos
    @Query("SELECT * FROM alumnos")
    suspend fun obtenerTodos(): List<Alumno>

    // Buscar por ID de alumno
    @Query("SELECT * FROM alumnos WHERE idAlumno = :idAlumno LIMIT 1")
    suspend fun buscarPorId(idAlumno: String): Alumno?

    // 🔍 Verificar login con ID y fecha de nacimiento
    @Query("SELECT * FROM alumnos WHERE idAlumno = :id AND fechaNacimiento = :fecha LIMIT 1")
    suspend fun verificarLogin(id: String, fecha: String): Alumno?
}


