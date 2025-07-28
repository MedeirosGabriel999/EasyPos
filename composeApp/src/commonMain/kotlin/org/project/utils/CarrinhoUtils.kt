// Funções utilitárias para manipular o carrinho de compras.
// Centraliza a lógica de adicionar e remover produtos.

package org.project.utils

import androidx.compose.runtime.snapshots.SnapshotStateList
import org.project.models.ItemCarrinho
import org.project.models.Produto

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