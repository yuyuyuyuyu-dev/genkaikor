package dev.yuyuyuyuyu.genkaikor.ui.openSourceLicenseList

import com.mikepenz.aboutlibraries.Libs
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen

data object OpenSourceLicenseListScreen : Screen {
    data class State(
        val libraries: Libs?,
    ) : CircuitUiState
}
