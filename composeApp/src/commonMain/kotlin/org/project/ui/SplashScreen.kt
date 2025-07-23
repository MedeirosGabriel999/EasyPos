package org.project.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.project.navigation.Screen

@Composable
fun SplashScreen(onNavigate: (Screen) -> Unit) {
    var visible by remember { mutableStateOf(false) }
    var indiceAtual by remember { mutableStateOf(0) }

    // Imagens para o carrossel
    val imagens = listOf(
        "banners/banner1.png",
        "banners/banner2.png",
        "banners/banner3.png"
    )

    // Inicia a animação e navegação após tempo
    LaunchedEffect(Unit) {
        visible = true
        delay(2500)
        onNavigate(Screen.PDV)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        // 🔄 Animação de carrossel no fundo
        if (visible) {
            LaunchedEffect(Unit) {
                while (true) {
                    delay(5000)
                    indiceAtual = (indiceAtual + 1) % imagens.size
                }
            }

            Crossfade(
                targetState = indiceAtual,
                animationSpec = tween(durationMillis = 1000)
            ) { indice ->
                Image(
                    painter = painterResource(imagens[indice]),
                    contentDescription = "Banner Splash",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }

        // 🔤 Texto central animado
        AnimatedVisibility(visible = visible, enter = fadeIn()) {
            Text(
                text = "EasyPOS",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}
