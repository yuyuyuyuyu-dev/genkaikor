package dev.yuyuyuyuyu.genkaikor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.ui.compose.produceLibraries
import dev.yuyuyuyuyu.genkaikor.ui.GenkaikorApp
import genkaikor.webapp.generated.resources.Res
import genkaikor.webapp.generated.resources.language_tag
import kotlinx.browser.document
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        val libraries by produceLibraries {
            Res.readBytes("files/aboutlibraries.json").decodeToString()
        }

        WebApp(libraries)
    }
}

@Composable
fun WebApp(libraries: Libs?) {
    val languageTag = stringResource(Res.string.language_tag)

    LaunchedEffect(languageTag) {
        if (languageTag.isNotEmpty()) {
            document.documentElement?.setAttribute("lang", languageTag)
        }
    }

    GenkaikorApp(libraries)
}
