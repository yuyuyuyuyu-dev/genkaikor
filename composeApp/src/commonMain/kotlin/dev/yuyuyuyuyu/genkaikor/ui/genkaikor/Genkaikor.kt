package dev.yuyuyuyuyu.genkaikor.ui.genkaikor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Genkaikor(state: GenkaikorScreen.State, modifier: Modifier = Modifier) = Box(
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
            label = { Text("濁点をつけたいテキストを入力してください") },
        )

        Text(state.genkaiText)

        Button(
            content = { Text("Copy to Clipboard") },
            onClick = { state.eventSink(GenkaikorScreen.Event.CopyButtonClicked) },
        )
    }
}
