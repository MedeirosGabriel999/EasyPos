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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.random.Random
import org.project.navigation.Screen
import org.project.ui.theme.LocalSpacing

/**
 * Tela responsável por gerenciar o fluxo de pagamento, exibindo um status de processamento,
 * sucesso ou erro, dependendo dos resultados simulados.
 *
 * @param onNavigate função que navega para uma nova tela baseada no evento ocorrido.
 * @param onFecharModal função chamada para fechar o modal de pagamento.
 * @param onResultado callback que retorna o resultado do pagamento, indicando se foi bem-sucedido (true) ou não (false).
 */
@Composable
fun PagamentoScreen(
    onNavigate: (Screen) -> Unit,
    onFecharModal: () -> Unit,
    onResultado: (sucesso: Boolean) -> Unit
) {
    val spacing = LocalSpacing.current
    var status by remember { mutableStateOf("processando") }

    // Simula processamento e resultado aleatório
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

    // Tela escurecida com card centralizado
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.7f)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .width(320.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(20.dp),
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
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(spacing.medium)
                        ) {
                            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                            Text(
                                "Processando pagamento...",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    "sucesso" -> {
                        Text(
                            "✅ Pagamento aprovado com sucesso!",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.tertiary,
                            textAlign = TextAlign.Center
                        )
                    }

                    "erro" -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(spacing.small)
                        ) {
                            Text(
                                "❌ Pagamento recusado!",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.error,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                "Tente novamente ou escolha outro método.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}
