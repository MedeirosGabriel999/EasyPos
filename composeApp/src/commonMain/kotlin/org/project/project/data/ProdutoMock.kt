package org.project.data

import easypos.composeapp.generated.resources.Res
import easypos.composeapp.generated.resources.agua1
import easypos.composeapp.generated.resources.batata1
import easypos.composeapp.generated.resources.batata2
import easypos.composeapp.generated.resources.burguer1
import easypos.composeapp.generated.resources.burguer2
import easypos.composeapp.generated.resources.burguer3
import easypos.composeapp.generated.resources.cebola1
import easypos.composeapp.generated.resources.fallback
import easypos.composeapp.generated.resources.milkshake1
import easypos.composeapp.generated.resources.refri1
import easypos.composeapp.generated.resources.salada1
import easypos.composeapp.generated.resources.sorvete1
import easypos.composeapp.generated.resources.suco1
import easypos.composeapp.generated.resources.wrap1
import org.project.models.Produto

val mockProdutos = listOf(
    Produto("1", "Hambúrguer Clássico", 19.90, Res.drawable.burguer1, "Lanches"),
    Produto("2", "Cheeseburguer Bacon", 22.50, Res.drawable.burguer2, "Lanches"),
    Produto("3", "Hambúrguer Duplo", 25.00, Res.drawable.burguer3, "Lanches"),
    Produto("4", "Batata Frita Pequena", 8.00, Res.drawable.batata1, "Acompanhamentos"),
    Produto("5", "Batata Frita Grande", 12.00, Res.drawable.batata2, "Acompanhamentos"),
    Produto("6", "Refrigerante Lata", 6.00, Res.drawable.refri1, "Bebidas"),
    Produto("7", "Suco Natural", 7.50, Res.drawable.suco1, "Bebidas"),
    Produto("8", "Água Mineral", 4.00, Res.drawable.agua1, "Bebidas"),
    Produto("9", "Milkshake Morango", 10.90, Res.drawable.milkshake1, "Bebidas"),
    Produto("10", "Sorvete Chocolate", 9.00, Res.drawable.sorvete1, "Sobremesas"),
    Produto("11", "Sorvete Napolitano", 9.00, Res.drawable.fallback, "Sobremesas"),
    Produto("12", "Brownie com Sorvete", 14.50, Res.drawable.fallback, "Sobremesas"),
    Produto("13", "Salada Caesar", 16.00, Res.drawable.salada1, "Lanches"),
    Produto("14", "Wrap de Frango", 17.90, Res.drawable.wrap1, "Lanches"),
    Produto("15", "Mini Cebolas Empanadas", 9.90, Res.drawable.cebola1, "Acompanhamentos")
)


