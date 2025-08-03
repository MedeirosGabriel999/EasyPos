package org.project.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.project.ui.theme.LocalSpacing

@Composable
fun CategoriaMenu(
    categoriaSelecionada: String?,
    onSelecionarCategoria: (String?) -> Unit,
    onFechar: () -> Unit
) {
    val spacing = LocalSpacing.current
    val categorias = listOf("Todas", "Lanches", "Bebidas", "Acompanhamentos", "Sobremesas")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.94f)),
        contentAlignment = Alignment.CenterStart
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .width(300.dp)
                .padding(spacing.medium),
            shape = MaterialTheme.shapes.extraLarge,
            color = MaterialTheme.colorScheme.surfaceVariant,
            tonalElevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(spacing.medium),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(spacing.medium)) {
                    Text(
                        text = "Categorias",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )

                    categorias.forEach { categoria ->
                        val selecionada = (categoria == "Todas" && categoriaSelecionada == null) || categoriaSelecionada == categoria

                        val backgroundColor =
                            if (selecionada) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.surface

                        val textColor =
                            if (selecionada) MaterialTheme.colorScheme.onPrimary
                            else MaterialTheme.colorScheme.onSurface

                        Surface(
                            shape = MaterialTheme.shapes.medium,
                            color = backgroundColor,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onSelecionarCategoria(if (categoria == "Todas") null else categoria)
                                }
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(vertical = 12.dp, horizontal = 16.dp)
                            ) {
                                Text(categoria, color = textColor)
                            }
                        }
                    }
                }

                Button(
                    onClick = onFechar,
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("Fechar")
                }
            }
        }
    }
}
