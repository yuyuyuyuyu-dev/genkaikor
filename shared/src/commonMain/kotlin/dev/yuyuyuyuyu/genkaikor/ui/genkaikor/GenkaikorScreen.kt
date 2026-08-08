package dev.yuyuyuyuyu.genkaikor.ui.genkaikor

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen

data object GenkaikorScreen : Screen {
    data class State(
        val genkaiText: String,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    sealed class Event : CircuitUiEvent {
        data class InputTextChanged(val newValue: String) : Event()
    }
}
