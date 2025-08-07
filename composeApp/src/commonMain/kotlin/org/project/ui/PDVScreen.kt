
// Tela principal do EasyPOS.
// Exibe lista de produtos, carrinho de compras, menu de categorias e tela de pagamento.
// Aplica tema visual consistente com tipografia, cores e espaçamentos.

package org.project.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
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
import org.project.data.mockProdutos
import org.project.models.ItemCarrinho
import org.project.navigation.Screen
import org.project.ui.components.CategoriaMenu
import org.project.ui.components.ItemCarrinhoView
import org.project.ui.components.ProdutoCard
import org.project.ui.theme.LocalSpacing
import org.project.utils.adicionarProduto
import org.project.utils.diminuirProduto
import org.project.utils.salvarVendaAtual

/**
 * Tela principal do Ponto de Venda (PDV) que gerencia a exibição de produtos, carrinho e fluxo de pagamento.
 *
 * @param isDarkTheme Função que controla a troca entre os temas claro e escuro do aplicativo.
 * @param onNavigate Função de callback usada para navegação entre diferentes telas da aplicação.
 */
@Composable
fun PDVScreen(
    isDarkTheme: (Boolean) -> Unit,
    onNavigate: (Screen) -> Unit
) {
    var theme by remember { mutableStateOf(false) } // false = Light, true = Dark
    val produtos = remember { mockProdutos }
    val carrinho = remember { mutableStateListOf<ItemCarrinho>() }
    var telaPagamentoFormas by remember { mutableStateOf(false) }
    var telaProcessandoPagamento by remember { mutableStateOf(false) }
    var categoriaSelecionada by remember { mutableStateOf<String?>(null) }
    var showMenu by remember { mutableStateOf(false) }

    val spacing = LocalSpacing.current

    // Cores personalizadas para os botões
    val verdeClaro = Color(0x9081C784) // Verde suave
    val vermelhoClaro = Color(0x90EF5350) // Vermelho suave

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

                RadioButton(
                    onClick = {
                        theme = !theme
                        isDarkTheme(theme)
                    },
                    selected = theme
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
                        .padding(horizontal = spacing.small)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(spacing.medium)
                ) {
                    Text(
                        "Produtos",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(vertical = spacing.small)
                    )

                    FlowRow(
                        modifier = Modifier.fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(spacing.medium),
                        verticalArrangement = Arrangement.spacedBy(spacing.medium)
                    ) {
                        produtos
                            .filter { categoriaSelecionada == null || it.categoria == categoriaSelecionada }
                            .forEach { produto ->
                                ProdutoCard(
                                    produto = produto,
                                    modifier = Modifier
                                        .width(180.dp) // Melhor para grid touch e responsividade
                                        .height(220.dp)
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
                        .padding(start = spacing.small)
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = MaterialTheme.shapes.large
                        ),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    // ITENS DO CARRINHO
                    Column(
                        modifier = Modifier
                            .padding(spacing.small)
                            .verticalScroll(rememberScrollState())
                            .weight(1f)
                            .animateContentSize(),
                        verticalArrangement = Arrangement.spacedBy(spacing.small)
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
                    Column(
                        modifier = Modifier
                            .padding(spacing.small)
                            .fillMaxWidth()
                            .background(
                                color = Color.Transparent,
                                shape = MaterialTheme.shapes.medium
                            )
                            .padding(spacing.medium)
                    ) {
                        val total = carrinho.sumOf { it.total }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Total:",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                "R$ %.2f".format(total),
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Spacer(modifier = Modifier.height(spacing.small))

                        // ROW COM BOTÕES FINALIZAR E LIMPAR
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(spacing.small)
                        ) {
                            // BOTÃO LIMPAR CARRINHO (VERMELHO CLARO)
                            Button(
                                onClick = {
                                    carrinho.clear()
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = vermelhoClaro,
                                    contentColor = Color.White,
                                    disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                                    disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                                ),
                                enabled = carrinho.isNotEmpty()
                            ) {
                                Text("Limpar", style = MaterialTheme.typography.bodyLarge)
                            }

                            // BOTÃO FINALIZAR VENDA (VERDE CLARO)
                            Button(
                                onClick = {
                                    if (carrinho.isNotEmpty()) {
                                        telaPagamentoFormas = true
                                    }
                                },
                                modifier = Modifier
                                    .weight(2f)
                                    .height(48.dp),
                                enabled = carrinho.isNotEmpty(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = verdeClaro,
                                    contentColor = Color.White,
                                    disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                                    disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                                )
                            ) {
                                Text("Finalizar Venda", style = MaterialTheme.typography.bodyLarge)
                            }
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
                            salvarVendaAtual(carrinho)
                            carrinho.clear()
                        } else {
                            telaPagamentoFormas = true
                        }
                    }
                )
            }
        }
    }
}