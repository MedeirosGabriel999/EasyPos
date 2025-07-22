package org.project.models


data class Produto(
    val id: String,
    val nome: String,
    val preco: Double,
    val imagem: String? = null,
    val categoria: String
)
