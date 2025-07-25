package org.project.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.project.models.Produto

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProdutoCard(
    produto: Produto,
    modifier: Modifier = Modifier,
    onAdicionar: (Produto) -> Unit
) {
    // 1. Crie uma fonte de interação. O 'remember' garante que ela persista.
    val interactionSource = remember { MutableInteractionSource() }

    // 2. Observe o estado "pressionado" a partir da fonte de interação.
    val isPressed by interactionSource.collectIsPressedAsState()

    // 3. Anime a escala com base no estado 'isPressed'.
    val scale by animateFloatAsState(targetValue = if (isPressed) 0.97f else 1f)
    Card(
        modifier = modifier
            .scale(scale) // Aplica a animação de escala
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(), // Efeito de ondulação do Material
                onClick = { onAdicionar(produto) }
            ),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A2A))
    ) {

        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Imagem do produto
            Image(
                painter = painterResource(produto.imagem ?: "images/fallback.png"),
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
