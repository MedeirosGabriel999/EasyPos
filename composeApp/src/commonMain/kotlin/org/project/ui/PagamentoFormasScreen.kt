package org.project.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.project.navigation.Screen

/**
 * Tela de pagamento que exibe as opções de formas de pagamento disponíveis
 * e um botão para cancelar a operação.
 *
 * @param onSelecionarForma Callback acionado quando uma das formas de pagamento é selecionada.
 *                          O parâmetro representa a tela que será exibida.
 * @param onCancelar Callback acionado ao cancelar o processo de pagamento.
 */
@Composable
fun PagamentoScreen(
    onSelecionarForma: (Screen) -> Unit,
    onCancelar: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .width(400.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Escolha a forma de pagamento",
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Button(
                    onClick = { onSelecionarForma(Screen.Pagamento) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    Text("Pix")
                }

                Button(
                    onClick = { onSelecionarForma(Screen.Pagamento) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    Text("Cartão de Crédito")
                }

                Button(
                    onClick = { onSelecionarForma(Screen.Pagamento) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    Text("Cartão de Débito")
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(
                    onClick = onCancelar,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cancelar", color = Color.Red)
                }
            }
        }
    }
}
