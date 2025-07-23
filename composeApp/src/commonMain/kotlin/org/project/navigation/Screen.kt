package org.project.navigation

sealed class Screen {
    object Splash : Screen()
    object PDV : Screen()
    object Carrinho : Screen()
    object Historico : Screen()
    data class DetalhesVenda(val idVenda: String) : Screen()
    object Pagamento : Screen()
}
