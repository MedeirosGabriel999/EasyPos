package org.project.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.project.models.Produto

@Composable
fun ProdutoCard(
    produto: Produto,
    onAdicionar: (Produto) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A2A))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagem do produto
            Image(
                painter = painterResource(produto.imagem ?: "images/fallback.png"),
                contentDescription = produto.nome,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(produto.nome, color = Color.White, fontSize = 18.sp)
                Text("R$ %.2f".format(produto.preco), color = Color(0xFFB0FFB0))
            }

            Button(
                onClick = { onAdicionar(produto) },
                modifier = Modifier.height(36.dp)
            ) {
                Text("Adicionar")
            }
        }
    }
}
