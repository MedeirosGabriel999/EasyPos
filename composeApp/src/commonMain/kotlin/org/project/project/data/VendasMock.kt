package org.project.data

import org.project.models.Produto
import org.project.models.Venda

val vendasMock = listOf(
    Venda(
        id = "001",
        data = "21/07/2025 14:30",
        total = 28.90,
        itens = listOf(
            Produto("1", "Hambúrguer Clássico", 19.90, null, "Lanches"),
            Produto("3", "Refrigerante Lata", 6.00, null, "Bebidas")
        )
    ),
    Venda(
        id = "002",
        data = "21/07/2025 15:10",
        total = 31.50,
        itens = listOf(
            Produto("2", "Cheeseburguer Bacon", 22.50, null, "Lanches"),
            Produto("4", "Suco Natural", 7.50, null, "Bebidas")
        )
    )
)
