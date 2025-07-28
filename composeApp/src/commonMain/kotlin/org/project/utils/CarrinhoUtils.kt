// Funções utilitárias para manipular o carrinho de compras.
// Centraliza a lógica de adicionar e remover produtos.

package org.project.utils

import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.project.data.VendasRepository
import org.project.models.ItemCarrinho
import org.project.models.Produto
import org.project.models.Venda
import kotlin.random.Random

fun adicionarProduto(produto: Produto, carrinho: SnapshotStateList<ItemCarrinho>) {
    val existente = carrinho.find { it.produto.id == produto.id }
    if (existente != null) {
        carrinho.remove(existente)
        carrinho.add(
            existente.copy(quantidade = existente.quantidade + 1)
        )
    } else {
        carrinho.add(ItemCarrinho(produto, 1))
    }
}


fun diminuirProduto(produto: Produto, carrinho: SnapshotStateList<ItemCarrinho>) {
    val existente = carrinho.find { it.produto.id == produto.id }
    if (existente != null) {
        carrinho.remove(existente)
        if (existente.quantidade > 1) {
            carrinho.add(
                existente.copy(quantidade = existente.quantidade - 1)
            )
        }
        // Se a quantidade for 1, ele simplesmente será removido (sem re-adicionar)
    }
}

private fun salvarVendaAtual(carrinho: List<ItemCarrinho>) {
    if (carrinho.isEmpty()) return

    val agora = Clock.System.now()
    val fusoHorario = TimeZone.currentSystemDefault()
    val dataHoraLocal = agora.toLocalDateTime(fusoHorario)

    val novaVenda = Venda(
        id = "V${Random.nextLong(100000, 999999)}", // ID aleatório simples
        data = "${dataHoraLocal.date} ${dataHoraLocal.hour}:${dataHoraLocal.minute}",
        total = carrinho.sumOf { it.total },
        itens = carrinho.map { it.produto }
    )

    VendasRepository.salvarVenda(novaVenda)
}