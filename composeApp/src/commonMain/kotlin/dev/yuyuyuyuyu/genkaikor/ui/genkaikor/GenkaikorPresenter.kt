package dev.yuyuyuyuyu.genkaikor.ui.genkaikor

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.presenter.Presenter

class GenkaikorPresenter : Presenter<GenkaikorScreen.State> {
    @Composable
    override fun present(): GenkaikorScreen.State {
        return GenkaikorScreen.State
    }
}
