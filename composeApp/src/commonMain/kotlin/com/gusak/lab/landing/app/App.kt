package com.gusak.lab.landing.app

import com.gusak.lab.landing.theme.GusakLabTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gusak.lab.landing.app.landing.LandingScreen

@Composable
@Preview
fun App() {
    GusakLabTheme {
        LandingScreen()
    }
}
