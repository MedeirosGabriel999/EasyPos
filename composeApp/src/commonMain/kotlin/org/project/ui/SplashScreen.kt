package org.project.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import easypos.composeapp.generated.resources.compose_multiplatform
import easypos.composeapp.generated.resources.Res
import easypos.composeapp.generated.resources.burguer1
import easypos.composeapp.generated.resources.burguer2
import easypos.composeapp.generated.resources.refri1
import easypos.composeapp.generated.resources.sorvete1
import easypos.composeapp.generated.resources.suco1
import kotlinx.coroutines.delay
import org.project.navigation.Screen
import org.project.ui.components.Animacao

@Composable
fun SplashScreen(onNavigate: (Screen) -> Unit) {
    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        visible = true
        delay(2500)


    }
    if( visible) {
        Animacao(modifier = Modifier, imagens =  listOf(
            Res.drawable.refri1,
            Res.drawable.suco1,
            Res.drawable.burguer1,
            Res.drawable.burguer2,
            Res.drawable.sorvete1
            // Placeholder for multiple images
        ), delay = 5000L)
    }else {


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
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
}