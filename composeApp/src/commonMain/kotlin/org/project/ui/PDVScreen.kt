// Tela principal do EasyPOS.
// Exibe lista de produtos, carrinho de compras, menu de categorias e tela de pagamento.
// Aplica tema visual consistente com tipografia, cores e espaçamentos.

package org.project.ui

import androidx.compose.animation.Crossfade
import org.project.ui.components.CategoriaMenu
import org.project.ui.components.ItemCarrinhoView
import androidx.compose.animation.animateContentSize
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
import org.project.data.mockProdutos
import org.project.models.ItemCarrinho
import org.project.models.Produto
import org.project.navigation.Screen
import org.project.ui.components.ProdutoCard
import org.project.ui.theme.LocalSpacing
import org.project.utils.adicionarProduto
import org.project.utils.diminuirProduto

@Composable
fun PDVScreen(onNavigate: (Screen) -> Unit) {
    val produtos = remember { mockProdutos }
    val carrinho = remember { mutableStateListOf<ItemCarrinho>() }
    var telaPagamentoFormas by remember { mutableStateOf(false) }
    var telaProcessandoPagamento by remember { mutableStateOf(false) }
    var categoriaSelecionada by remember { mutableStateOf<String?>(null) }
    var showMenu by remember { mutableStateOf(false) }

    val spacing = LocalSpacing.current

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {

            // TOPO COM TÍTULO E BOTÃO DE MENU
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(spacing.small),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { showMenu = true }) {
                    Text(
                        "⋮",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.width(spacing.small))
                Text(
                    "EasyPOS",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            // CORPO: PRODUTOS E CARRINHO
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(spacing.medium)
            ) {
                // PRODUTOS
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxHeight()
                        .padding(horizontal = spacing.medium)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(spacing.medium)
                ) {
                    Text(
                        "Produtos",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(vertical = spacing.medium)
                    )
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp), // Espaçamento horizontal
                        verticalArrangement = Arrangement.spacedBy(12.dp)  // Espaçamento vertical
                    ) {
                        produtos
                            .filter { categoriaSelecionada == null || it.categoria == categoriaSelecionada }
                            .forEach { produto ->
                                // 4. Passe um Modifier para o ProdutoCard definindo seu tamanho
                                ProdutoCard(
                                    produto = produto,
                                    modifier = Modifier.width(220.dp) // Defina a largura de cada card
                                ) {
                                    adicionarProduto(it, carrinho)
                                }
                            }
                    }
                }

                // CARRINHO
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(start = spacing.medium)
                        .background(MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.large),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    // ITENS DO CARRINHO
                    Column(
                        modifier = Modifier
                            .padding(spacing.medium)
                            .verticalScroll(rememberScrollState())
                            .weight(1f)
                            .animateContentSize()
                    ) {
                        carrinho.forEach { item ->
                            ItemCarrinhoView(
                                item = item,
                                onAdicionar = { adicionarProduto(item.produto, carrinho) },
                                onRemover = { diminuirProduto(item.produto, carrinho) }
                            )
                        }
                    }

                    // TOTAL E PAGAMENTO
                    Column(modifier = Modifier.padding(spacing.medium)) {
                        val total = carrinho.sumOf { it.total }
                        Text(
                            "Total: R$ %.2f".format(total),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Button(
                            onClick = { telaPagamentoFormas = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = spacing.small)
                        ) {
                            Text("Pagar")
                        }
                    }
                }
            }
        }

        // MENU DE CATEGORIAS SOBREPOSTO
        if (showMenu) {
            CategoriaMenu(
                categoriaSelecionada = categoriaSelecionada,
                onSelecionarCategoria = {
                    categoriaSelecionada = it
                    showMenu = false
                },
                onFechar = { showMenu = false }
            )
        }

        // TELA DE FORMAS DE PAGAMENTO
        Crossfade(
            targetState = when {
                telaPagamentoFormas -> "formas"
                telaProcessandoPagamento -> "processando"
                else -> null
            },
            label = "CrossfadePagamento"
        ) { estado ->
            when (estado) {
                "formas" -> PagamentoScreen(
                    onSelecionarForma = {
                        telaPagamentoFormas = false
                        telaProcessandoPagamento = true
                    },
                    onCancelar = {
                        telaPagamentoFormas = false
                    }
                )

                "processando" -> PagamentoScreen(
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
        }}}

