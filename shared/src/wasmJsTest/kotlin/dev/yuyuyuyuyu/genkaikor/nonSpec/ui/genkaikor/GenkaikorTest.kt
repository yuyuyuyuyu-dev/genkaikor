package dev.yuyuyuyuyu.genkaikor.nonSpec.ui.genkaikor

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.requestFocus
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.test.waitUntilExactlyOneExists
import dev.yuyuyuyuyu.genkaikor.ui.GenkaikorApp
import genkaikor.shared.generated.resources.Res
import genkaikor.shared.generated.resources.copy_to_clipboard
import org.jetbrains.compose.resources.getString
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class GenkaikorTest {
    @Test
    fun `copy button should be disabled before any text is typed`() =
        runComposeUiTest {
            // Arrange
            setContent {
                GenkaikorApp(libraries = null)
            }

            // Act
            waitUntilExactlyOneExists(hasText(getString(Res.string.copy_to_clipboard)))

            // Assert
            onNodeWithText(getString(Res.string.copy_to_clipboard)).assertIsNotEnabled()
        }

    @Test
    fun `should show the typed text with dakuten`() =
        runComposeUiTest {
            // Arrange
            setContent {
                GenkaikorApp(libraries = null)
            }

            // Act
            onNode(hasSetTextAction()).performTextInput("ことりんっっっ！")

            // Assert
            onNodeWithText("こ゛と゛り゛ん゛っ゛っ゛っ゛！").assertExists()
        }

    @Test
    fun `text field should lose focus when the done key is pressed`() =
        runComposeUiTest {
            // Arrange
            setContent {
                GenkaikorApp(libraries = null)
            }
            onNode(hasSetTextAction()).requestFocus()

            // Act
            onNode(hasSetTextAction()).performImeAction()

            // Assert
            onNode(hasSetTextAction()).assertIsNotFocused()
        }
}
