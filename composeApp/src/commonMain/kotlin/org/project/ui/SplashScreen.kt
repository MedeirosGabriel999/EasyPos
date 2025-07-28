// Tela inicial do EasyPOS com carrossel de imagens.
// Usa Crossfade para alternar visualmente e vai para PDV ao clique do usuário.

package org.project.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import easypos.composeapp.generated.resources.Res
import easypos.composeapp.generated.resources.banner1
import easypos.composeapp.generated.resources.banner2
import easypos.composeapp.generated.resources.banner3
import easypos.composeapp.generated.resources.banners
import easypos.composeapp.generated.resources.burguer1
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.project.navigation.Screen

private const val CAROUSEL_DELAY_MS = 3000L

@Composable
fun SplashScreen(onNavigate: (Screen) -> Unit) {

    // Lista de imagens do carrossel
    val imagens = listOf(
        Res.drawable.banner1,
        Res.drawable.banner2,
        Res.drawable.banner3,
        Res.drawable.banner1
    )

    var index by remember { mutableStateOf(0) }

    // Controla a troca automática das imagens
    LaunchedEffect(Unit) {
        while (true) {
            delay(CAROUSEL_DELAY_MS)
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
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()


//                    .padding(32.dp)
            )
        }
    }
}
