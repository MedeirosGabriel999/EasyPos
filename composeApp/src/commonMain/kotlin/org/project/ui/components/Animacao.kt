package org.project.ui.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun Animacao(
    imagens: List<DrawableResource>,
    modifier: Modifier = Modifier,
    delayMs: Long = 8000L
) {
    if (imagens.isEmpty()) return

    var indiceAtual by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = imagens) {
        while (true) {
            delay(delayMs)
            indiceAtual = (indiceAtual + 1) % imagens.size
        }
    }

    Box(modifier = modifier) {
        Crossfade(
            targetState = indiceAtual,
            animationSpec = tween(durationMillis = 1000)
        ) { indice ->
            Image(
                painter = painterResource(imagens[indice]),
                contentDescription = "Imagem do carrossel",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}
