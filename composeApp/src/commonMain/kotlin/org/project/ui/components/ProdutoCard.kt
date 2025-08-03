package org.project.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Shape
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import easypos.composeapp.generated.resources.Res
import easypos.composeapp.generated.resources.fallback
import org.project.models.Produto
import org.project.ui.theme.LocalSpacing

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ProdutoCard(
    produto: Produto,
    modifier: Modifier = Modifier,
    onAdicionar: (Produto) -> Unit
) {
    val spacing = LocalSpacing.current
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(if (isPressed) 0.97f else 1f)

    val imagemPainter = produto.imagem?.let { painterResource(it) } ?: painterResource(Res.drawable.fallback)

    val shape: Shape = RoundedCornerShape(16.dp)

    Card(
        modifier = modifier.run {
            scale(scale)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = { onAdicionar(produto) }
                )
        },
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = imagemPainter,
                contentDescription = produto.nome,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape)
            )

            Spacer(modifier = Modifier.height(spacing.small))

            Text(
                text = produto.nome,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2
            )

            Text(
                text = "R$ %.2f".format(produto.preco),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
