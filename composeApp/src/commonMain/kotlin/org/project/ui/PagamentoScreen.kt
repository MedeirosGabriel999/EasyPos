// Tela flutuante de processamento e resultado de pagamento.
// Exibe status "processando", "sucesso" ou "erro" com feedback visual animado.

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
import kotlinx.coroutines.delay
import kotlin.random.Random
import org.project.navigation.Screen
import org.project.ui.theme.LocalSpacing

@Composable
fun PagamentoScreen(
    onNavigate: (Screen) -> Unit,
    onFecharModal: () -> Unit,
    onResultado: (sucesso: Boolean) -> Unit
) {
    val spacing = LocalSpacing.current
    var status by remember { mutableStateOf("processando") }

    // Simula processamento com delay e resultado aleatório
    LaunchedEffect(Unit) {
        delay(2000)
        val sucesso = Random.nextInt(100) < 50
        status = if (sucesso) "sucesso" else "erro"

        delay(3000)

        if (sucesso) {
            onResultado(true)
            onNavigate(Screen.PDV)
        } else {
            onResultado(false)
        }
    }

    // Tela escurecida com modal central
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xAA000000)), // fundo translúcido
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .width(360.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.large),
                contentAlignment = Alignment.Center
            ) {
                when (status) {
                    "processando" -> {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(spacing.medium))
                            Text(
                                "Processando pagamento...",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    "sucesso" -> {
                        Text(
                            "✅ Pagamento aprovado com sucesso!",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }

                    "erro" -> {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                "❌ Pagamento recusado!",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.error
                            )
                            Spacer(modifier = Modifier.height(spacing.small))
                            Text(
                                "Tente novamente ou escolha outro método.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}
