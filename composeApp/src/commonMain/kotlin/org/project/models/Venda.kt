package org.project.models

import kotlinx.serialization.Serializable


@Serializable
data class Venda(
    val id: String,
    val data: String,
    val total: Double,
    val itens: List<ProdutoVenda>
)
