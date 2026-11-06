package dev.yuyuyuyuyu.genkaikor.nonSpec.ui

import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.click
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isPopup
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.requestFocus
import androidx.compose.ui.test.v2.runComposeUiTest
import dev.yuyuyuyuyu.genkaikor.ui.GenkaikorApp
import genkaikor.shared.generated.resources.Res
import genkaikor.shared.generated.resources.app_name
import genkaikor.shared.generated.resources.open_source_licenses
import org.jetbrains.compose.resources.getString
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class GenkaikorAppTest {
    @Test
    fun `should show the open source licenses screen when it is chosen from the menu`() =
        runComposeUiTest {
            // Arrange
            setContent {
                GenkaikorApp(libraries = null)
            }

            // Act
            chooseOpenSourceLicensesFromMenu()

            // Assert
            onNodeWithText(getString(Res.string.open_source_licenses)).assertExists()
            onNodeWithContentDescription("navigate back").assertExists()
        }

    @Test
    fun `should return to the main screen with the typed text kept when back is pressed`() =
        runComposeUiTest {
            // Arrange
            setContent {
                GenkaikorApp(libraries = null)
            }
            onNode(hasSetTextAction()).performTextInput("ことりんっっっ！")
            chooseOpenSourceLicensesFromMenu()

            // Act
            onNodeWithContentDescription("navigate back").performClick()

            // Assert
            onNodeWithText(getString(Res.string.app_name)).assertExists()
            onNodeWithContentDescription("navigate back").assertDoesNotExist()
            onNode(hasSetTextAction()).assert(hasText("ことりんっっっ！"))
        }

    @Test
    fun `should return to the main screen in one step after the licenses are chosen twice`() =
        runComposeUiTest {
            // Arrange
            setContent {
                GenkaikorApp(libraries = null)
            }
            chooseOpenSourceLicensesFromMenu()
            chooseOpenSourceLicensesFromMenu()

            // Act
            onNodeWithContentDescription("navigate back").performClick()

            // Assert
            onNodeWithText(getString(Res.string.app_name)).assertExists()
            onNodeWithContentDescription("navigate back").assertDoesNotExist()
        }

    @Test
    fun `should open the source code page when it is chosen from the menu`() =
        runComposeUiTest {
            // Arrange
            recordOpenedUrl()
            setContent {
                GenkaikorApp(libraries = null)
            }

            // Act
            chooseSourceCodeFromMenu()

            // Assert
            assertEquals("https://github.com/yuyuyuyuyu-dev/genkaikor", openedUrl())
        }

    @Test
    fun `text field should lose focus when the screen outside it is tapped`() =
        runComposeUiTest {
            // Arrange
            setContent {
                GenkaikorApp(libraries = null)
            }
            onNode(hasSetTextAction()).requestFocus()

            // Act
            onRoot().performTouchInput { click(centerLeft) }

            // Assert
            onNode(hasSetTextAction()).assertIsNotFocused()
        }

    private fun ComposeUiTest.chooseOpenSourceLicensesFromMenu() = chooseFromMenu(0)

    private fun ComposeUiTest.chooseSourceCodeFromMenu() = chooseFromMenu(1)

    private fun ComposeUiTest.chooseFromMenu(index: Int) {
        onNodeWithContentDescription("menu").performClick()
        onAllNodes(hasClickAction() and hasAnyAncestor(isPopup()))[index].performClick()
    }
}

@OptIn(ExperimentalWasmJsInterop::class)
@JsFun(
    """() => {
        window.open = (url) => {
            globalThis.openedUrl = url;
            return null;
        };
    }""",
)
private external fun recordOpenedUrl()

@OptIn(ExperimentalWasmJsInterop::class)
@JsFun("() => globalThis.openedUrl ?? null")
private external fun openedUrl(): String?
