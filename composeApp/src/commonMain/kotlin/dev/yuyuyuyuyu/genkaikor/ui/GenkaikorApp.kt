package dev.yuyuyuyuyu.genkaikor.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import dev.yuyuyuyuyu.genkaikor.di.domainModule
import dev.yuyuyuyuyu.genkaikor.di.uiModule
import dev.yuyuyuyuyu.genkaikor.ui.genkaikor.GenkaikorScreen
import dev.yuyuyuyuyu.genkaikor.ui.openSourceLicenseList.OpenSourceLicenseListScreen
import dev.yuyuyuyuyu.mymaterialtheme.MyMaterialTheme
import dev.yuyuyuyuyu.simpleTopAppBar.SimpleTopAppBar
import genkaikor.composeapp.generated.resources.Res
import genkaikor.composeapp.generated.resources.app_name
import genkaikor.composeapp.generated.resources.open_source_licenses
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
fun GenkaikorApp() {
    val backStack = rememberSaveableBackStack(root = GenkaikorScreen)
    val navigator = rememberCircuitNavigator(backStack) {}
    val currentScreen = backStack.topRecord?.screen

    val uriHandler = LocalUriHandler.current

    KoinApplication(
        application = {
            printLogger()
            modules(uiModule, domainModule)
        }
    ) {
        MyMaterialTheme {
            Scaffold(
                topBar = {
                    SimpleTopAppBar(
                        title = when (currentScreen) {
                            is OpenSourceLicenseListScreen -> stringResource(Res.string.open_source_licenses)
                            else -> stringResource(Res.string.app_name)
                        },
                        navigateBackIsPossible = backStack.size > 1,
                        onNavigateBackButtonClick = {
                            navigator.pop()
                        },
                        onOpenSourceLicensesButtonClick = {
                            navigator.goTo(OpenSourceLicenseListScreen)
                        },
                        onSourceCodeButtonClick = {
                            uriHandler.openUri("https://github.com/yuyuyuyuyu-dev/genkaikor")
                        },
                    )
                },
            ) { innerPadding ->
                CircuitCompositionLocals(circuit = koinInject()) {
                    NavigableCircuitContent(navigator, backStack, Modifier.padding(innerPadding))
                }
            }
        }
    }
}
