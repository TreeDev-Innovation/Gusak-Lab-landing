package com.gusak.lab.landing.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object GusakLabColors {
    val Light = lightColorScheme(
        primary = Color(0xFF6750a4),
        onPrimary = Color(0xFFFFFFFF),
        secondary = Color(0xFF625B71),
        onSecondary = Color(0xFFFFFFFF),
        background = Color(0xFFFFFBFE),
        onBackground = Color(0xFF1C1B1F),
        surface = Color(0xFFFFFBFE),
        onSurface = Color(0xFF1C1B1F),
        surfaceVariant = Color(0xFFE7E0EC),
        onSurfaceVariant = Color(0xFF49454E),
        error = Color(0xFFB3261E),
        onError = Color(0xFFFFFFFF)
    )

    val Dark = darkColorScheme(
        primary = Color(0xFFD0BCFF),
        onPrimary = Color(0xFF381E72),
        secondary = Color(0xFFCCC7DB),
        onSecondary = Color(0xFF332D41),
        background = Color(0xFF1C1B1F),
        onBackground = Color(0xFFE6E1E6),
        surface = Color(0xFF1C1B1F),
        onSurface = Color(0xFFE6E1E6),
        surfaceVariant = Color(0xFF49454E),
        onSurfaceVariant = Color(0xFFCAC7D0),
        error = Color(0xFFF2B8B5),
        onError = Color(0xFF601410)
    )
}
