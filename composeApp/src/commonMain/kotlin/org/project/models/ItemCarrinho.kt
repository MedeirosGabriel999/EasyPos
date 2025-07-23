package org.project.models

data class ItemCarrinho(
    val produto: Produto,
    var quantidade: Int
) {
    val total: Double
        get() = produto.preco * quantidade
}
