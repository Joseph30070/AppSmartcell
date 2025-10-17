package com.example.smartcellapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.smartcellapp.data.entity.Curso

@Dao
interface CursoDao {

    // Insertar un curso
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarCurso(curso: Curso)

    // Insertar varios cursos (por ejemplo, datos iniciales)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarCursos(cursos: List<Curso>)

    // Obtener todos los cursos
    @Query("SELECT * FROM cursos")
    suspend fun obtenerTodos(): List<Curso>

    // Buscar curso por su nombre
    @Query("SELECT * FROM cursos WHERE nombreCurso = :nombre LIMIT 1")
    suspend fun buscarPorNombre(nombre: String): Curso?


    @Query("SELECT * FROM cursos WHERE idCurso = :idCurso LIMIT 1")
    suspend fun obtenerCursoPorId(idCurso: Int): Curso?

}
