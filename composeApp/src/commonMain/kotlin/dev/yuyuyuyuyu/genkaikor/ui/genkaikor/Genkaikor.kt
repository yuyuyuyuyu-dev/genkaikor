package dev.yuyuyuyuyu.genkaikor.ui.genkaikor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import dev.yuyuyuyuyu.genkaikor.ui.components.CopyToClipboardButton
import genkaikor.composeapp.generated.resources.Res
import genkaikor.composeapp.generated.resources.genkaikor_input_label
import org.jetbrains.compose.resources.stringResource

@Composable
fun Genkaikor(state: GenkaikorScreen.State, modifier: Modifier = Modifier) {
    val snackbarHostState = remember { SnackbarHostState() }
    val focusManager = LocalFocusManager.current

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { innerPadding ->
        Box(
            modifier = modifier.fillMaxSize().padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            var inputText by rememberSaveable { mutableStateOf("") }

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TextField(
                    value = inputText,
                    onValueChange = { newValue ->
                        inputText = newValue
                        state.eventSink(GenkaikorScreen.Event.InputTextChanged(newValue))
                    },
                    label = { Text(stringResource(Res.string.genkaikor_input_label)) },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                )

                Text(state.genkaiText)

                CopyToClipboardButton(
                    textToCopy = state.genkaiText,
                    enabled = state.genkaiText.isNotEmpty(),
                    snackbarHostState = snackbarHostState,
                )
            }
        }
    }
}
