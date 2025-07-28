// Componente visual que representa um item do carrinho.
// Exibe nome, quantidade, preço total e botões + e -
// Aplica tema visual e animação suave ao modificar.

package org.project.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.project.models.ItemCarrinho
import org.project.ui.theme.LocalSpacing

@Composable
fun ItemCarrinhoView(
    item: ItemCarrinho,
    onAdicionar: () -> Unit,
    onRemover: () -> Unit
) {
    val spacing = LocalSpacing.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = spacing.small)
            .animateContentSize(), // anima alterações no conteúdo
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "${item.produto.nome} x${item.quantidade}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f)
        )

        Text(
            "R$ %.2f".format(item.total),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(end = spacing.small)
        )

        Row {
            Button(
                onClick = onRemover,
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier.size(32.dp)
            ) {
                Text("−")
            }
            Spacer(modifier = Modifier.width(4.dp))
            Button(
                onClick = onAdicionar,
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier.size(32.dp)
            ) {
                Text("+")
            }
        }
    }
}
