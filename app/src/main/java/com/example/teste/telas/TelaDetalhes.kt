package com.example.teste.telas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.teste.model.Tarefa

@Composable
fun TelaDetalhes(
    modifier: Modifier = Modifier,
    tarefa: Tarefa,
    onVoltar: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tarefa Selecionada:",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = tarefa.titulo,
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Status Estilizado
        val statusText = if (tarefa.concluida) "Concluída" else "Pendente"
        val statusColor = if (tarefa.concluida) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error

        SuggestionChip(
            onClick = { },
            label = { Text(statusText) },
            colors = SuggestionChipDefaults.suggestionChipColors(labelColor = statusColor)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onVoltar,
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text("Voltar")
        }
    }
}
