package dev.yuyuyuyuyu.genkaikor.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import dev.yuyuyuyuyu.genkaikor.di.uiModule
import dev.yuyuyuyuyu.genkaikor.ui.genkaikor.GenkaikorScreen
import dev.yuyuyuyuyu.mymaterialtheme.MyMaterialTheme
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
fun GenkaikorApp() {
    val backStack = rememberSaveableBackStack(root = GenkaikorScreen)
    val navigator = rememberCircuitNavigator(backStack) {}

    KoinApplication(
        application = {
            printLogger()
            modules(uiModule)
        }
    ) {
        MyMaterialTheme {
            Scaffold(
                topBar = {},
            ) { innerPadding ->
                CircuitCompositionLocals(circuit = koinInject()) {
                    NavigableCircuitContent(navigator, backStack, Modifier.padding(innerPadding))
                }
            }
        }
    }
}
