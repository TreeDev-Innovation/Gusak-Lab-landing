package com.gusak.lab.landing

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.gusak.lab.landing.app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "GusakLab",
    ) {
        App()
    }
}