package com.example.smartcellapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matriculas")
data class Matricula(
    @PrimaryKey(autoGenerate = true)
    val idMatricula: Int = 0,  // PK autogenerado

    val codAlumno: String,     // FK → Alumnos.cod_alumno
    val idCurso: Int,          // FK → Cursos.id_curso
    val fechaMatricula: String // Fecha de matrícula
)
