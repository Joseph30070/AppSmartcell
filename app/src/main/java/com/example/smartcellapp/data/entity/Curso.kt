package com.example.smartcellapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cursos")
data class Curso(
    @PrimaryKey(autoGenerate = true)
    val idCurso: Int = 0,
    val nombreCurso: String
)
