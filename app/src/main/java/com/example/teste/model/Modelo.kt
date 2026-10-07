package com.example.teste.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "tarefas")
data class Tarefa(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val titulo: String,
    val concluida: Boolean = false
)
