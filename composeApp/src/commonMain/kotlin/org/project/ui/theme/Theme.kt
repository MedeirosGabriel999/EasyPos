package org.project.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF58D68D),
    secondary = Color(0xFFA9CCE3),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color(0xFFF0F0F0),
    error = Color(0xFFEC7063)
)


private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF28B463),
    secondary = Color(0xFF5DADE2),
    background = Color(0xFFF7F9F9),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color(0xFF1C1C1C),
    onSurface = Color(0xFF1C1C1C),
    error = Color(0xFFE74C3C)
)


@Composable
fun EasyPOSTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val AppTypography = Typography(
        titleLarge = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = colorScheme.onSurface // Cor do tema
        ),
        bodyLarge = TextStyle(
            fontSize = 16.sp,
            color = colorScheme.onSurface
        ),
        bodyMedium = TextStyle(
            fontSize = 14.sp,
            color = colorScheme.onSurface.copy(alpha = 0.7f)
        ),
        labelSmall = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = colorScheme.onSurface.copy(alpha = 0.7f)
        )
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}