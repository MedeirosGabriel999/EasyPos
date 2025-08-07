package org.project.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Define o esquema de cores utilizado na interface do tema escuro.
 *
 * Este esquema organiza as cores para diferentes elementos da interface seguindo o guideline
 * do Material Design 3. Ele inclui cores principais para fundo, superfície, componentes interativos,
 * mensagens de erro e texto, adaptados para um tema escuro.
 *
 * - `primary`: Representa a cor principal da aplicação no tema escuro.
 * - `secondary`: Utilizado para realces menores ou secundários na interface.
 * - `background`: Cor aplicada ao fundo da interface.
 * - `surface`: Utilizada para superfícies de elevação padrão.
 * - `surfaceVariant`: Variante de superfícies para delimitar áreas específicas.
 * - `primaryContainer`: Usado para caixas ou superfícies que centralizam elementos primários.
 * - `onPrimary`: Cor do texto ou ícones que aparecem sobre a cor primaria.
 * - `onSecondary`: Cor do texto ou ícones que aparecem sobre a cor secundária.
 * - `onBackground`: Cor do texto ou ícones que aparecem sobre o fundo.
 * - `onSurface`: Cor do texto ou ícones que aparecem sobre a superfície.
 * - `onPrimaryContainer`: Cor do texto ou ícones sobre o container primário.
 * - `error`: Cor utilizada para estados de erro, como falhas de validação.
 */
// Tema Escuro
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF34C759),         // Verde
    secondary = Color(0xFF5E5CE6),       // Roxo/Azul
    background = Color(0xFF1C1C1E),      // Preto
    surface = Color(0xFF2C2C2E),         // Cinza Escuro
    surfaceVariant = Color(0xFF3A3A3C),  // Cinza Médio
    primaryContainer = Color(0xFF4CD964), // Verde Claro
    onPrimary = Color.Black,             // Preto
    onSecondary = Color.White,           // Branco
    onBackground = Color.White,          // Branco
    onSurface = Color(0xFFE5E5EA),       // Cinza Claro
    onPrimaryContainer = Color.Black,     // Preto
    error = Color(0xFFFF453A)            // Vermelho
)

/**
 * Representa o esquema de cores utilizado no tema claro da interface.
 *
 * O esquema define as cores principais e de suporte para diversos componentes
 * da aplicação no tema claro, incluindo fundo, superfície, contêineres de
 * cores primárias e secundárias, textos, e mensagens de erro.
 *
 * Este esquema de cores é aplicado automaticamente no tema claro através do
 * composable `EasyPOSTheme`.
 */
// Tema Claro
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF34C759),         // Verde
    secondary = Color(0xFF5E5CE6),       // Roxo/Azul
    background = Color(0xFFF8F9FA),      // Cinza Muito Claro
    surface = Color.White,               // Branco
    surfaceVariant = Color(0xFFF2F2F7),  // Cinza Claro
    primaryContainer = Color(0xFFE6F8EC), // Verde Muito Claro
    onPrimary = Color.White,             // Branco
    onSecondary = Color.White,           // Branco
    onBackground = Color(0xFF1C1C1E),    // Preto
    onSurface = Color(0xFF1C1C1E),       // Preto
    onPrimaryContainer = Color(0xFF1C1C1E), // Preto
    error = Color(0xFFFF3B30)            // Vermelho
)

/**
 * Define tipografia refinada para usos diversos na interface do usuário, proporcionando
 * melhor legibilidade e uma hierarquia visual clara. Cada estilo de texto possui uma
 * combinação específica de tamanho, peso, espaçamento entre linhas e, opcionalmente, cor.
 *
 * A tipografia é composta por diferentes categorias, incluindo:
 * - `displayLarge`: Usado principalmente para títulos ou cabeçalhos de maior destaque.
 * - `titleLarge`: Indicado para títulos de seções ou elementos importantes.
 * - `titleMedium`: Utilizado para subtítulos ou títulos menos proeminentes.
 * - `bodyLarge`: Padrão para textos de corpo principal.
 * - `bodyMedium`: Usado para textos de corpo secundário, com cor cinza para menor destaque.
 * - `labelSmall`: Voltado para rótulos ou textos descritivos menores.
 *
 * Esse conjunto de estilos é utilizado para manter consistência visual em toda a aplicação.
 */
// Tipografia refinada para melhor legibilidade e hierarchy
private val AppTypography = Typography(
    displayLarge = TextStyle(
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 36.sp
    ),
    titleLarge = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 22.sp
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
        color = Color.Gray
    ),
    labelSmall = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Gray
    )
)

/**
 * Aplica o tema visual do EasyPOS, alternando entre o tema claro e escuro com base no parâmetro fornecido.
 *
 * @param darkTheme Define se o tema escuro será utilizado. Se não for fornecido, o tema será determinado automaticamente pelo sistema.
 * @param content Uma função composable que representa o conteúdo que será exibido dentro do tema especificado.
 */
@Composable
fun EasyPOSTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}