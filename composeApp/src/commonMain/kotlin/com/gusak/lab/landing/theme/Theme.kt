package com.gusak.lab.landing.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun GusakLabTheme(darkTheme: Boolean = false, content: @Composable () -> Unit) {
    val colors = if (darkTheme) GusakLabColors.Dark else GusakLabColors.Light

    MaterialTheme(
        colorScheme = colors,
        typography = MaterialTheme.typography,
        content = content
    )
}
