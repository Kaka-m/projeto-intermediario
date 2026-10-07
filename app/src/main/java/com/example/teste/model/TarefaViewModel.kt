package com.example.teste.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TarefaViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).tarefaDao()

    val tarefas: StateFlow<List<Tarefa>> = dao.listarTodas()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun adicionarTarefa(titulo: String) {
        if (titulo.isBlank()) return
        viewModelScope.launch {
            dao.inserir(Tarefa(titulo = titulo))
        }
    }

    fun alternarStatus(tarefa: Tarefa) {
        viewModelScope.launch {
            dao.atualizar(tarefa.copy(concluida = !tarefa.concluida))
        }
    }

    fun excluirTarefa(tarefa: Tarefa) {
        viewModelScope.launch {
            dao.excluir(tarefa)
        }
    }
}
