package dev.yuyuyuyuyu.genkaikor.spec

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.v2.runComposeUiTest
import dev.yuyuyuyuyu.genkaikor.ui.GenkaikorApp
import genkaikor.shared.generated.resources.Res
import genkaikor.shared.generated.resources.copied_to_clipboard
import genkaikor.shared.generated.resources.copy_to_clipboard
import org.jetbrains.compose.resources.getString
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class CopyGenkaiTextSpec {
    @Test
    fun `should copy the typed text with dakuten when the copy button is pressed`() =
        runComposeUiTest {
            // Arrange
            recordTextWrittenToClipboard()
            setContent {
                GenkaikorApp(libraries = null)
            }

            // Act
            onNode(hasSetTextAction()).performTextInput("ことりんっっっ！")
            onNodeWithText(getString(Res.string.copy_to_clipboard)).performClick()

            // Assert
            assertEquals("こ゛と゛り゛ん゛っ゛っ゛っ゛！", textWrittenToClipboard())
            onNodeWithText(getString(Res.string.copied_to_clipboard)).assertExists()
        }
}

@OptIn(ExperimentalWasmJsInterop::class)
@JsFun(
    """() => {
        navigator.clipboard.writeText = (text) => {
            globalThis.textWrittenToClipboard = text;
            return Promise.resolve();
        };
    }""",
)
private external fun recordTextWrittenToClipboard()

@OptIn(ExperimentalWasmJsInterop::class)
@JsFun("() => globalThis.textWrittenToClipboard ?? null")
private external fun textWrittenToClipboard(): String?
