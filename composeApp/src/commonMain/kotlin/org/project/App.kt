package org.project

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import org.project.navigation.Screen
import org.project.ui.*
import org.project.ui.theme.*


@Composable
fun App() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Splash) }

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = AzulClaro,
            background = Preto,
            surface = Color(0xFF1E1E1E),
            error = VermelhoErro,
            onPrimary = Branco,
            onSurface = Branco
        ),
        typography = AppTypography
    ) {
        when (val screen = currentScreen) {
            is Screen.Splash -> SplashScreen(onNavigate = { currentScreen = it })

            is Screen.PDV -> PDVScreen(onNavigate = { currentScreen = it })

            is Screen.Historico -> HistoricoScreen()

            is Screen.Carrinho -> TODO("Tela do carrinho separada (se houver)")

            is Screen.DetalhesVenda -> TODO("Tela detalhes da venda para ID: ${screen.idVenda}")

            is Screen.FormasPagamento -> PagamentoScreen(
                onSelecionarForma = { currentScreen = it },
                onCancelar = { currentScreen = Screen.PDV }
            )

            is Screen.Pagamento -> PagamentoScreen(
                onNavigate = { currentScreen = it },
                onFecharModal = {},
                onResultado = { sucesso ->


                }
            )
        }
    }
}
