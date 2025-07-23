package org.project.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.project.navigation.Screen
import kotlin.random.Random

@Composable
fun PagamentoScreen(onNavigate: (Screen) -> Unit) {
    var status by remember { mutableStateOf("processando") }

    LaunchedEffect(Unit) {
        delay(2000)
        val sucesso = Random.nextInt(100) < 80
        status = if (sucesso) "sucesso" else "erro"
        delay(3000)
        onNavigate(Screen.PDV)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        when (status) {
            "processando" -> {
                Text(
                    text = "Processando pagamento...",
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }

            "sucesso" -> {
                Text(
                    text = "Pagamento aprovado com sucesso! \uD83C\uDF54✅",
                    fontSize = 22.sp,
                    color = Color(0xFF4CAF50),
                    fontWeight = FontWeight.Bold
                )
            }

            "erro" -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Ops! Pagamento recusado \uD83D\uDE25",
                        fontSize = 22.sp,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Tente novamente ou escolha outro método.",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}
