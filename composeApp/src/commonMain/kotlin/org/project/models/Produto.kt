package org.project.models

import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource


data class Produto(
    val id: String,
    val nome: String,
    val preco: Double,
    val imagem: DrawableResource? = null,
    val categoria: String
)

@Serializable
data class ProdutoVenda(
    val id: String,
    val nome: String,
    val preco: Double,
    val categoria: String
)
