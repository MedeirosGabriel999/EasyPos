// Tela inicial do EasyPOS com carrossel de imagens.
// Usa Crossfade para alternar visualmente e vai para PDV ao clique do usuário.

package org.project.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.project.navigation.Screen

@Composable
fun SplashScreen(onNavigate: (Screen) -> Unit) {
    // Lista de imagens do carrossel
    val imagens = listOf(
        "banners/banner1.png",
        "banners/banner2.png",
        "banners/banner3.png",
        "banners/banner4.png",
    )

    var index by remember { mutableStateOf(0) }

    // Controla a troca automática das imagens
    LaunchedEffect(Unit) {
        while (true) {
            delay(2500) // troca a cada 2,5 segundos
            index = (index + 1) % imagens.size
        }
    }

    // Tela clicável para prosseguir
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onNavigate(Screen.PDV) }, // vai para PDV ao clicar
        contentAlignment = Alignment.Center
    ) {
        Crossfade(targetState = imagens[index], label = "SplashCarrossel") { imagem ->
            Image(
                painter = painterResource(imagem),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            )
        }
    }
}
