package dev.yuyuyuyuyu.genkaikor

import androidx.compose.runtime.getValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.mikepenz.aboutlibraries.ui.compose.produceLibraries
import dev.yuyuyuyuyu.genkaikor.ui.GenkaikorApp
import genkaikor.desktopapp.generated.resources.Res

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Genkaikor",
    ) {
        val libraries by produceLibraries {
            Res.readBytes("files/aboutlibraries.json").decodeToString()
        }

        GenkaikorApp(libraries)
    }
}
