// Componente visual do produto usado na tela de vendas.
// Mostra imagem, nome, preço e botão de adicionar.
// Aplica tema global de tipografia, cores e espaçamento para manter consistência visual.

package org.project.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.project.models.Produto
import org.project.ui.theme.LocalSpacing

@Composable
fun ProdutoCard(
    produto: Produto,
    onAdicionar: (Produto) -> Unit
) {
    val spacing = LocalSpacing.current

    Card(
        modifier = Modifier
            .padding(spacing.small)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.medium), // aplicando padding consistente
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagem do produto com fallback
            Image(
                painter = painterResource(produto.imagem ?: "images/fallback.png"),
                contentDescription = produto.nome,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Gray) // opcional: pode trocar por MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(spacing.medium))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = produto.nome,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(spacing.small))
                Text(
                    text = "R$ %.2f".format(produto.preco),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.width(spacing.medium))

            Button(
                onClick = { onAdicionar(produto) },
                modifier = Modifier.height(36.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Adicionar")
            }
        }
    }
}
