// Componente visual do menu de categorias sobreposto.
// Mostra botões para filtrar produtos por categoria e botão de fechar.

package org.project.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.project.ui.theme.LocalSpacing

@Composable
fun CategoriaMenu(
    categoriaSelecionada: String?,
    onSelecionarCategoria: (String?) -> Unit,
    onFechar: () -> Unit
) {
    val spacing = LocalSpacing.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xAA000000)), // Fundo escuro translúcido
        contentAlignment = Alignment.CenterStart
    ) {
        Card(
            modifier = Modifier
                .width(300.dp)
                .fillMaxHeight(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(spacing.medium),
                verticalArrangement = Arrangement.spacedBy(spacing.medium)
            ) {
                Text(
                    "Categorias",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                listOf("Todas", "Lanches", "Bebidas", "Sobremesas").forEach { categoria ->
                    val isSelected = categoriaSelecionada == categoria || (categoria == "Todas" && categoriaSelecionada == null)

                    Button(
                        onClick = {
                            val nova = if (categoria == "Todas") null else categoria
                            onSelecionarCategoria(nova)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.surfaceVariant
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(categoria, color = MaterialTheme.colorScheme.onPrimary)
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                OutlinedButton(
                    onClick = onFechar,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Fechar", color = MaterialTheme.colorScheme.onSurface)
                }
            }
        }
    }
}
