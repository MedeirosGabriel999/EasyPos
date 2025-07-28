package org.project.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import easypos.composeapp.generated.resources.Res
import easypos.composeapp.generated.resources.fallback
import org.jetbrains.compose.resources.painterResource
import org.project.models.Produto

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProdutoCard(
    produto: Produto,
    modifier: Modifier = Modifier,
    onAdicionar: (Produto) -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(targetValue = if (isPressed) 0.97f else 1f)

    Card(
        modifier = modifier
            .scale(scale)
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(),
                onClick = { onAdicionar(produto) }
            ),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A2A))
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Imagem do produto
            Image(
                painter = painterResource(produto.imagem ?: Res.drawable.fallback),
                contentDescription = produto.nome,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .size(80.dp)
//                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.height(3.dp))

//            Column(
//                modifier = Modifier.weight(1f)
//            ) {
            Text(produto.nome, color = Color.White, fontSize = 18.sp)
            Text("R$ %.2f".format(produto.preco), color = Color(0xFFB0FFB0))
//            }

//            Button(
//                onClick = { onAdicionar(produto) },
//                modifier = Modifier.height(36.dp)
//            ) {
//                Text("Adicionar")
//            }
        }
    }
}
