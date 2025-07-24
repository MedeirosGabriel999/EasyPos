package org.project.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.project.data.mockProdutos
import org.project.models.ItemCarrinho
import org.project.models.Produto
import org.project.navigation.Screen
import org.project.ui.components.ProdutoCard

@Composable
fun PDVScreen(onNavigate: (Screen) -> Unit) {
    val produtos = remember { mockProdutos }
    val carrinho = remember { mutableStateListOf<ItemCarrinho>() }
    var telaPagamentoFormas by remember { mutableStateOf(false) }
    var telaProcessandoPagamento by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1C1C1C))
                .padding(12.dp)
        ) {
            // CATEGORIAS
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(end = 8.dp)
                    .background(Color(0xFF2E2E2E)),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Categorias", fontSize = 20.sp, color = Color.White, modifier = Modifier.padding(top = 12.dp))
                listOf("Lanches", "Bebidas", "Sobremesas").forEach { categoria ->
                    Button(
                        onClick = { /* Em breve: filtro */ },
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(48.dp)
                    ) {
                        Text(categoria)
                    }
                }
            }

            // PRODUTOS
            Column(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Produtos", fontSize = 20.sp, color = Color.White, modifier = Modifier.padding(vertical = 12.dp))
                produtos.forEach { produto ->
                    ProdutoCard(produto = produto) {
                        adicionarProduto(it, carrinho)
                    }
                }
            }

            // CARRINHO
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
                    carrinho.forEach { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("${item.produto.nome} x${item.quantidade}", color = Color.White, modifier = Modifier.weight(1f))
                            Text("R$ %.2f".format(item.total), color = Color.White, modifier = Modifier.padding(end = 8.dp))
                            Row {
                                Button(
                                    onClick = { diminuirProduto(item.produto, carrinho) },
                                    contentPadding = PaddingValues(0.dp),
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Text("−")
                                }
                                Spacer(modifier = Modifier.width(4.dp))
                                Button(
                                    onClick = { adicionarProduto(item.produto, carrinho) },
                                    contentPadding = PaddingValues(0.dp),
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Text("+")
                                }
                            }
                        }
                    }
                }

                Column(modifier = Modifier.padding(12.dp)) {
                    val total = carrinho.sumOf { it.total }
                    Text("Total: R$ %.2f".format(total), fontSize = 18.sp, color = Color.Yellow)
                    Button(
                        onClick = { telaPagamentoFormas = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {
                        Text("Pagar")
                    }
                }
            }
        }

        // Tela flutuante: Formas de pagamento
        if (telaPagamentoFormas) {
            PagamentoScreen(
                onSelecionarForma = {
                    telaPagamentoFormas = false
                    telaProcessandoPagamento = true
                },
                onCancelar = {
                    telaPagamentoFormas = false
                }
            )
        }

        // Tela flutuante: Processando pagamento
        if (telaProcessandoPagamento) {
            PagamentoScreen(
                onNavigate = onNavigate,
                onFecharModal = {
                    telaProcessandoPagamento = false
                },
                onResultado = { sucesso ->
                    telaProcessandoPagamento = false
                    if (sucesso) {
                        carrinho.clear()
                    } else {
                        telaPagamentoFormas = true
                    }
                }
            )
        }
    }
}

// ADICIONAR PRODUTO
private fun adicionarProduto(produto: Produto, carrinho: SnapshotStateList<ItemCarrinho>) {
    val existente = carrinho.find { it.produto.id == produto.id }
    if (existente != null) {
        existente.quantidade++
        carrinho.remove(existente)
        carrinho.add(existente)
    } else {
        carrinho.add(ItemCarrinho(produto, 1))
    }
}

// DIMINUIR PRODUTO
private fun diminuirProduto(produto: Produto, carrinho: SnapshotStateList<ItemCarrinho>) {
    val existente = carrinho.find { it.produto.id == produto.id }
    if (existente != null) {
        existente.quantidade--
        carrinho.remove(existente)
        if (existente.quantidade > 0) {
            carrinho.add(existente)
        }
    }
}
