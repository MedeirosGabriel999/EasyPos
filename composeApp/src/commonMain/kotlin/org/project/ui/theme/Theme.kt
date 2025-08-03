package org.project.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Tema Escuro
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF34C759),
    secondary = Color(0xFF5E5CE6),
    background = Color(0xFF1C1C1E),
    surface = Color(0xFF2C2C2E),
    surfaceVariant = Color(0xFF3A3A3C),
    primaryContainer = Color(0xFF4CD964),
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color(0xFFE5E5EA),
    onPrimaryContainer = Color.Black,
    error = Color(0xFFFF453A)
)

// Tema Claro
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF34C759),
    secondary = Color(0xFF5E5CE6),
    background = Color(0xFFF8F9FA),
    surface = Color.White,
    surfaceVariant = Color(0xFFF2F2F7),
    primaryContainer = Color(0xFFE6F8EC),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color(0xFF1C1C1E),
    onSurface = Color(0xFF1C1C1E),
    onPrimaryContainer = Color(0xFF1C1C1E),
    error = Color(0xFFFF3B30)
)

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
