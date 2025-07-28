package org.project.models

import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource

@Serializable
data class Produto(
    val id: String,
    val nome: String,
    val preco: Double,
    val imagem: DrawableResource? = null,
    val categoria: String
)
