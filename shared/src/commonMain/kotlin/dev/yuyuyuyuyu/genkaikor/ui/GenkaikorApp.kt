package dev.yuyuyuyuyu.genkaikor.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.mikepenz.aboutlibraries.Libs
import dev.yuyuyuyuyu.genkaikor.di.domainModule
import dev.yuyuyuyuyu.genkaikor.di.uiModule
import dev.yuyuyuyuyu.genkaikor.ui.genkaikor.Genkaikor
import dev.yuyuyuyuyu.genkaikor.ui.genkaikor.GenkaikorViewModel
import dev.yuyuyuyuyu.genkaikor.ui.openSourceLicenseList.OpenSourceLicenseList
import dev.yuyuyuyuyu.mymaterialtheme.MyMaterialTheme
import dev.yuyuyuyuyu.simpleTopAppBar.SimpleTopAppBar
import genkaikor.shared.generated.resources.Res
import genkaikor.shared.generated.resources.app_name
import genkaikor.shared.generated.resources.open_source_licenses
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.koin.dsl.koinConfiguration

@Composable
fun GenkaikorApp(libraries: Libs?) {
    val backStack = remember { mutableStateListOf<Route>(Route.Genkaikor) }

    val focusManager = LocalFocusManager.current
    val uriHandler = LocalUriHandler.current

    KoinApplication(
        configuration =
            koinConfiguration {
                modules(uiModule, domainModule)
            },
    ) {
        MyMaterialTheme {
            Scaffold(
                modifier =
                    Modifier.clickable(
                        interactionSource = null,
                        indication = null,
                        onClick = { focusManager.clearFocus() },
                    ),
                topBar = {
                    SimpleTopAppBar(
                        title =
                            when (backStack.last()) {
                                Route.Genkaikor -> stringResource(Res.string.app_name)
                                Route.OpenSourceLicenseList -> stringResource(Res.string.open_source_licenses)
                            },
                        navigateBackIsPossible = backStack.size > 1,
                        onNavigateBackButtonClick = {
                            backStack.removeLastOrNull()
                        },
                        onOpenSourceLicensesButtonClick = {
                            if (backStack.last() != Route.OpenSourceLicenseList) {
                                backStack.add(Route.OpenSourceLicenseList)
                            }
                        },
                        onSourceCodeButtonClick = {
                            uriHandler.openUri("https://github.com/yuyuyuyuyu-dev/genkaikor")
                        },
                    )
                },
            ) { innerPadding ->
                GenkaikorNavDisplay(
                    backStack = backStack,
                    libraries = libraries,
                    modifier = Modifier.padding(innerPadding),
                )
            }
        }
    }
}

@Composable
private fun GenkaikorNavDisplay(
    backStack: SnapshotStateList<Route>,
    libraries: Libs?,
    modifier: Modifier = Modifier,
) {
    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators =
            listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
        entryProvider =
            entryProvider {
                entry<Route.Genkaikor> {
                    val viewModel = koinViewModel<GenkaikorViewModel>()
                    Genkaikor(
                        inputText = viewModel.inputText,
                        genkaiText = viewModel.genkaiText,
                        onInputTextChange = viewModel::changeInputText,
                    )
                }
                entry<Route.OpenSourceLicenseList> {
                    OpenSourceLicenseList(libraries)
                }
            },
    )
}
