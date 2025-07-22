package org.project.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.project.data.vendasMock
import org.project.models.Venda

@Composable
fun HistoricoScreen() {
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

        vendasMock.forEach { venda ->
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
    }
}
