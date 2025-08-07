// Tela de splash inicial com animação e clique para entrar.
// Mostra imagens em carrossel com Crossfade e botão para prosseguir.

package org.project.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import easypos.composeapp.generated.resources.*
import org.project.navigation.Screen
import org.project.ui.theme.LocalSpacing

/**
 * Tela de splash inicial que exibe uma sequência de banners de forma dinâmica, permite
 * interação do usuário por toque e navega para outra tela após a interação.
 *
 * @param onNavigate Função que será chamada ao concluir a interação na SplashScreen,
 * passando como parâmetro a tela destino do tipo [Screen].
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
fun SplashScreen(onNavigate: (Screen) -> Unit) {
    val spacing = LocalSpacing.current
    val banners = listOf(
        Res.drawable.banner1,
        Res.drawable.banner2,
        Res.drawable.banner3,
        Res.drawable.banner4,

    )

    var index by remember { mutableStateOf(0) }
    var showContinue by remember { mutableStateOf(false) }

    // Crossfade entre as imagens a cada 2.5s
    LaunchedEffect(Unit) {
        while (!showContinue) {
            delay(2500)
            index = (index + 1) % banners.size
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .clickable { showContinue = true },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize(),
//                .padding(spacing.large),
            verticalArrangement = Arrangement.Center
        ) {
            Crossfade(
                targetState = index,
                label = "SplashBannerCrossfade"
            ) { i ->
                Image(
                    painter = painterResource(banners[i]),
                    contentDescription = "Banner ${i + 1}",
                    modifier = Modifier
                        .fillMaxSize(), // preenche toda a tela
                    contentScale = ContentScale.FillBounds
                )
            }

            Spacer(modifier = Modifier.height(spacing.large))

            if (showContinue) {
                Text(
                    "Toque para continuar",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }

    // Navegar após o clique
    if (showContinue) {
        LaunchedEffect(true) {
            delay(300)
            onNavigate(Screen.PDV)
        }
    }
}
