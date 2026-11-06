package dev.yuyuyuyuyu.genkaikor.nonSpec.ui.openSourceLicenseList

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.isPopup
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.v2.runComposeUiTest
import com.mikepenz.aboutlibraries.Libs
import dev.yuyuyuyuyu.genkaikor.ui.GenkaikorApp
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class OpenSourceLicenseListTest {
    @Test
    fun `should list libraries that share a name only once`() =
        runComposeUiTest {
            // Arrange
            val libraries =
                Libs
                    .Builder()
                    .withJson(
                        """
                        {
                          "libraries": [
                            {"uniqueId": "example:first", "name": "Example Library", "licenses": []},
                            {"uniqueId": "example:second", "name": "Example Library", "licenses": []}
                          ],
                          "licenses": {}
                        }
                        """.trimIndent(),
                    ).build()
            setContent {
                GenkaikorApp(libraries)
            }

            // Act
            onNodeWithContentDescription("menu").performClick()
            onAllNodes(hasClickAction() and hasAnyAncestor(isPopup()))[0].performClick()

            // Assert
            onAllNodesWithText("Example Library").assertCountEquals(1)
        }
}
