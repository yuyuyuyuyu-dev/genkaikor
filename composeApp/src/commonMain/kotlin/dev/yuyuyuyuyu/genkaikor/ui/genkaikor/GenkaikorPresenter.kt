package dev.yuyuyuyuyu.genkaikor.ui.genkaikor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.slack.circuit.runtime.presenter.Presenter
import dev.yuyuyuyuyu.genkaikor.domain.useCase.InsertDakutenUseCase

class GenkaikorPresenter(
    private val insertDakutenUseCase: InsertDakutenUseCase,
) : Presenter<GenkaikorScreen.State> {
    @Composable
    override fun present(): GenkaikorScreen.State {
        var genkaiText by rememberSaveable { mutableStateOf("") }

        return GenkaikorScreen.State(genkaiText) { event ->
            when (event) {
                is GenkaikorScreen.Event.InputTextChanged -> {
                    genkaiText = insertDakutenUseCase(event.newValue)
                }
            }
        }
    }
}
