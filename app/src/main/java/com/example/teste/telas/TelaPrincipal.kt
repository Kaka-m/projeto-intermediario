package com.example.teste.telas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.teste.model.Tarefa

@Composable
fun TelaListaPrincipal(
    modifier: Modifier = Modifier,
    lista: List<Tarefa>,
    onAdicionarTarefa: (String) -> Unit,
    onAlternarStatus: (String) -> Unit,
    onExcluirTarefa: (String) -> Unit,
    onSelecionarTarefa: (Tarefa) -> Unit
) {
    // Estado local para o input de texto
    var textoNovaTarefa by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Área de Input (TextField + Botão)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = textoNovaTarefa,
                onValueChange = { textoNovaTarefa = it },
                label = { Text("Nova Tarefa") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    if (textoNovaTarefa.isNotBlank()) {
                        onAdicionarTarefa(textoNovaTarefa)
                        textoNovaTarefa = "" // Limpa o campo após adicionar
                    }
                },
                modifier = Modifier.height(56.dp)
            ) {
                Text("Adicionar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Exibição da Lista com LazyColumn
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(lista, key = { it.id }) { tarefa ->
                ItemTarefaRow(
                    tarefa = tarefa,
                    onAlternarStatus = { onAlternarStatus(tarefa.id) },
                    onExcluir = { onExcluirTarefa(tarefa.id) },
                    onCliqueLinha = { onSelecionarTarefa(tarefa) }
                )
            }
        }
    }
}

@Composable
fun ItemTarefaRow(
    tarefa: Tarefa,
    onAlternarStatus: () -> Unit,
    onExcluir: () -> Unit,
    onCliqueLinha: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCliqueLinha() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = tarefa.concluida,
                onCheckedChange = { onAlternarStatus() }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = tarefa.titulo,
                modifier = Modifier.weight(1f),
                fontSize = 18.sp,
                textDecoration = if (tarefa.concluida) TextDecoration.LineThrough else TextDecoration.None,
                color = if (tarefa.concluida) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.onSurface
            )

            IconButton(onClick = onExcluir) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Excluir Tarefa",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
