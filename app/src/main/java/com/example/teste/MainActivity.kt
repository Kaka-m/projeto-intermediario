package com.example.teste

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.teste.uiprojeto.telas.ToDoListApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Se o Android Studio gerou um tema para si (ex: TesteTheme), use-o aqui.
            // Caso contrário, pode chamar diretamente a função:
            ToDoListApp()
        }
    }
}