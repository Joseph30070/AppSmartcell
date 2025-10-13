package com.example.smartcellapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alumnos")
data class Alumno(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idAlumno: String,
    val nombre: String,
    val apellido: String,
    val dni: String,
    val correo: String,
    val celular: String,
    val cursoMatriculado: String,
    val fechaNacimiento: String
)



