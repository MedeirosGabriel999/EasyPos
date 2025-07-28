// Define os espaçamentos padrões para o layout da interface.
// Esses valores são usados com Modifier.padding() para manter a consistência entre os elementos.

package org.project.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

data class Spacing(
    val small: androidx.compose.ui.unit.Dp = 8.dp,
    val medium: androidx.compose.ui.unit.Dp = 16.dp,
    val large: androidx.compose.ui.unit.Dp = 24.dp
)

val LocalSpacing = staticCompositionLocalOf { Spacing() }
