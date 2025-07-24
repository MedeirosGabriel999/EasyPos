package org.project.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random
import org.project.navigation.Screen

@Composable
fun PagamentoScreen(
    onNavigate: (Screen) -> Unit,
    onFecharModal: () -> Unit,
    onResultado: (sucesso: Boolean) -> Unit
) {
    var status by remember { mutableStateOf("processando") }

    // Simula o processamento do pagamento
    LaunchedEffect(Unit) {
        delay(2000)
        val sucesso = Random.nextInt(100) < 50 // 50% de chance de sucesso
        status = if (sucesso) "sucesso" else "erro"

        delay(3000)

        if (sucesso) {
            onResultado(true)             // limpa carrinho (opcional)
            onNavigate(Screen.PDV)        // volta para PDV
        } else {
            onResultado(false)            // não limpa carrinho

        }
    }

    // Modal flutuante com feedback visual
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xAA000000)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .width(400.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                when (status) {
                    "processando" -> {
                        Text(
                            text = "Processando pagamento...",
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    }

                    "sucesso" -> {
                        Text(
                            text = "✅ Pagamento aprovado com sucesso!",
                            fontSize = 20.sp,
                            color = Color(0xFF4CAF50)
                        )
                    }

                    "erro" -> {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "❌ Pagamento recusado!",
                                fontSize = 20.sp,
                                color = Color.Red
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
    }
}
