package dev.yuyuyuyuyu.genkaikor

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.yuyuyuyuyu.genkaikor.ui.GenkaikorApp

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Genkaikor",
    ) {
        GenkaikorApp()
    }
}
