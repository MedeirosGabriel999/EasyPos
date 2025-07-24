package org.project

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import org.project.navigation.Screen
import org.project.ui.*


@Composable
fun App() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Splash) }

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Color(0xFF00BCD4),
            background = Color(0xFF121212),
            surface = Color(0xFF1E1E1E),
            onPrimary = Color.White,
            onBackground = Color.White,
            onSurface = Color.White
        ),
        typography = Typography()
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
