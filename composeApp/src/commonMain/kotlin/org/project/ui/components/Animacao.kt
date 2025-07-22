package org.project.ui.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun Animacao(
    modifier: Modifier = Modifier,
    imagens: List<DrawableResource>,
    delay: Long = 10000L
) {
    if (imagens.isEmpty()) {
        return
    }
    var indiceAtual by remember { mutableStateOf(0) }
    LaunchedEffect(key1 = delay) {
        while (true) {
            delay(delay)
            indiceAtual = (indiceAtual + 1) % imagens.size
        }
    }

    Box(modifier = modifier) {
        Crossfade(
            targetState = indiceAtual,
            animationSpec = tween(durationMillis = 1000)
        ) { indiceDaImagem ->
            Image(
                painter = painterResource(imagens[indiceDaImagem]),
                contentDescription = "Imagem do carrossel",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}