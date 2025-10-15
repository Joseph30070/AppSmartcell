package com.example.smartcellapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "alumnos",
    foreignKeys = [
        ForeignKey(
            entity = Curso::class,
            parentColumns = ["idCurso"],
            childColumns = ["idCurso"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class Alumno(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val idAlumno: String,
    val nombre: String,
    val apellido: String,
    val dni: String,
    val correo: String,
    val celular: String,

    // Relación con la tabla Cursos
    val idCurso: Int? = null,

    val fechaNacimiento: String
)




