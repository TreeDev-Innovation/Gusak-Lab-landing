package com.letit0or1.kawa.playable.web.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val PlayableDarkColorScheme = darkColorScheme(
    primary = Color(0xFF8BC34A),
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF385723),
    onPrimaryContainer = Color(0xFFDCECCB),

    secondary = Color.White,
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF424242),
    onSecondaryContainer = Color.White,

    tertiary = Color(0xFFFFD700),
    onTertiary = Color(0xFF1A1400),
    tertiaryContainer = Color(0xFFFFE44D),
    onTertiaryContainer = Color(0xFFDAA520),

    background = Color(0xFF121212),
    onBackground = Color.White,
    surface = Color(0xFF121212),
    onSurface = Color.White,
    surfaceContainer = Color(0xFF212121),
    surfaceContainerLow = Color(0xFF1F1F1F),

    outline = Color(0x99FFFFFF),

    error = Color(0xFFCF6679),
    onError = Color.Black,
    errorContainer = Color(0xFFB00020),
    onErrorContainer = Color.White
)

@Composable
fun PlayableTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = PlayableDarkColorScheme,
        content = content
    )
}
