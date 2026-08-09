package dev.yuyuyuyuyu.genkaikor

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import dev.yuyuyuyuyu.genkaikor.ui.GenkaikorApp

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        GenkaikorApp()
    }
}
