package com.example.smartcellapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.smartcellapp.data.entity.Matricula

@Dao
interface MatriculaDao {

    // Insertar una matrícula
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarMatricula(matricula: Matricula)

    // Consultar todas las matrículas
    @Query("SELECT * FROM matriculas")
    suspend fun obtenerTodas(): List<Matricula>

    // Buscar matrícula por código de alumno
    @Query("SELECT * FROM matriculas WHERE codAlumno = :codAlumno")
    suspend fun obtenerPorAlumno(codAlumno: String): List<Matricula>

    // Buscar matrícula por curso
    @Query("SELECT * FROM matriculas WHERE idCurso = :idCurso")
    suspend fun obtenerPorCurso(idCurso: Int): List<Matricula>
}
