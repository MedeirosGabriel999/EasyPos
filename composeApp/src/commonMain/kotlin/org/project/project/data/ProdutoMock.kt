package org.project.data

import easypos.composeapp.generated.resources.Res
import easypos.composeapp.generated.resources.burguer1
import easypos.composeapp.generated.resources.burguer2
import easypos.composeapp.generated.resources.refri1
import easypos.composeapp.generated.resources.sorvete1
import easypos.composeapp.generated.resources.suco1
import org.project.models.Produto

val mockProdutos = listOf(
    Produto("1", "Hambúrguer Clássico", 19.90, Res.drawable.burguer1, "Lanches"),
    Produto("2", "Cheeseburguer Bacon", 22.50, Res.drawable.burguer2, "Lanches"),
    Produto("3", "Refrigerante Lata", 6.00, Res.drawable.refri1, "Bebidas"),
    Produto("4", "Suco Natural", 7.50, Res.drawable.suco1, "Bebidas"),
    Produto("5", "Sorvete Chocolate", 9.00, Res.drawable.sorvete1, "Sobremesas")
)
