package org.project.models

data class Venda(
    val id: String,
    val data: String,
    val total: Double,
    val itens: List<Produto>
)
