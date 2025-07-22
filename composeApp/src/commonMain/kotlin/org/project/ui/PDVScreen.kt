package org.project.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.project.data.mockProdutos
import org.project.models.Produto
import org.project.ui.components.ProdutoCard

@Composable
fun PDVScreen() {
    val produtos = remember { mockProdutos }
    val carrinho = remember { mutableStateListOf<Produto>() }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1C))
            .padding(12.dp)
    ) {
        // COLUNA 1 - Categorias (mock)
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(end = 8.dp)
                .background(Color(0xFF2E2E2E)),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Categorias",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 12.dp)
            )

            listOf("Lanches", "Bebidas", "Sobremesas").forEach { categoria ->
                Button(
                    onClick = { /* Filtro de categoria (em breve) */ },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(48.dp)
                ) {
                    Text(categoria)
                }
            }
        }

        // COLUNA 2 - Produtos
        Column(
            modifier = Modifier
                .weight(2f)
                .fillMaxHeight()
                .padding(horizontal = 8.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                "Produtos",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(vertical = 12.dp)
            )

            produtos.forEach { produto ->
                ProdutoCard(produto = produto) {
                    carrinho.add(it)
                    println("Produto adicionado: ${it.nome}")
                }
            }
        }

        // COLUNA 3 - Carrinho
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(start = 8.dp)
                .background(Color(0xFF2E2E2E)),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .verticalScroll(rememberScrollState())
                    .weight(1f)
            ) {
                Text("Carrinho", fontSize = 20.sp, color = Color.White)

                carrinho.forEach {
                    Text(
                        text = "${it.nome} - R$ %.2f".format(it.preco),
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }

            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                val total = carrinho.sumOf { it.preco }
                Text("Total: R$ %.2f".format(total), fontSize = 18.sp, color = Color.Yellow)
                Button(
                    onClick = { println("Pagamento simulado") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text("Pagar")
                }
            }
        }
    }
}
