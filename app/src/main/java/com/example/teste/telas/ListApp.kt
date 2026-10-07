package com.example.teste.uiprojeto.telas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.teste.telas.TelaListaPrincipal
import com.example.teste.telas.TelaDetalhes
import com.example.teste.model.Tarefa
import com.example.teste.model.TarefaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListApp(viewModel: TarefaViewModel = viewModel()) {
    // Observa a lista do banco de dados (MVVM)
    val listaDeTarefas by viewModel.tarefas.collectAsState()
    var tarefaSelecionada by remember { mutableStateOf<Tarefa?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (tarefaSelecionada == null) "Minhas Tarefas" else "Detalhes da Tarefa") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        if (tarefaSelecionada == null) {
            TelaListaPrincipal(
                modifier = Modifier.padding(paddingValues),
                lista = listaDeTarefas,
                onAdicionarTarefa = { titulo -> viewModel.adicionarTarefa(titulo) },
                onAlternarStatus = { id ->
                    listaDeTarefas.find { it.id == id }?.let { viewModel.alternarStatus(it) }
                },
                onExcluirTarefa = { id ->
                    listaDeTarefas.find { it.id == id }?.let { viewModel.excluirTarefa(it) }
                },
                onSelecionarTarefa = { tarefa -> tarefaSelecionada = tarefa }
            )
        } else {
            TelaDetalhes(
                modifier = Modifier.padding(paddingValues),
                tarefa = tarefaSelecionada!!,
                onVoltar = { tarefaSelecionada = null }
            )
        }
    }
}