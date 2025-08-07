package org.project.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.project.data.VendasRepository

/**
 * Composable responsável por exibir uma tela de histórico de vendas.
 *
 * A tela apresenta uma lista de vendas recuperadas através do método `lerVendas` do
 * repositório `VendasRepository`. Cada venda é exibida em um cartão contendo informações
 * detalhadas, como identificador da venda, data, valor total e itens vendidos.
 *
 * O layout é organizado em uma coluna que ocupa a área total disponível e permite rolagem
 * vertical. Cada cartão contém detalhes estilizados como título, cor e espaçamento entre
 * itens, oferecendo uma interface visual agradável e estruturada.
 *
 * Detalhes exibidos para cada venda:
 * - ID da venda
 * - Data da venda
 * - Valor total formatado em reais
 * - Lista de itens vendidos
 *
 * Requer o uso do Compose para renderizar o conteúdo e gerenciar os componentes declarativos.
 */
@Composable
fun HistoricoScreen() {
    val vendasSalvas = VendasRepository.lerVendas()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            "Histórico de Vendas",
            fontSize = 24.sp,
            color = Color.White
        )

        vendasSalvas.sortedBy { it.data }.forEach { venda ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A2A))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Venda #${venda.id}", fontSize = 18.sp, color = Color.White)
                    Text("Data: ${venda.data}", color = Color.LightGray)
                    Text("Total: R$ %.2f".format(venda.total), color = Color.Yellow)

                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Itens:", color = Color.White)

                    venda.itens.forEach {
                        Text("- ${it.nome}", color = Color.White, fontSize = 14.sp)
                    }
                }
            }

        }

//        vendasMock.forEach { venda ->
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .wrapContentHeight(),
//                colors = CardDefaults.cardColors(containerColor = Color(0xFF2A2A2A))
//            ) {
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Text("Venda #${venda.id}", fontSize = 18.sp, color = Color.White)
//                    Text("Data: ${venda.data}", color = Color.LightGray)
//                    Text("Total: R$ %.2f".format(venda.total), color = Color.Yellow)
//
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Text("Itens:", color = Color.White)
//
//                    venda.itens.forEach {
//                        Text("- ${it.nome}", color = Color.White, fontSize = 14.sp)
//                    }
//                }
//            }
//        }
    }
}
