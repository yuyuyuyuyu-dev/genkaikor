package dev.yuyuyuyuyu.genkaikor.di

import com.slack.circuit.foundation.Circuit
import dev.yuyuyuyuyu.genkaikor.ui.genkaikor.Genkaikor
import dev.yuyuyuyuyu.genkaikor.ui.genkaikor.GenkaikorPresenter
import dev.yuyuyuyuyu.genkaikor.ui.genkaikor.GenkaikorScreen
import org.koin.dsl.module

val uiModule = module {
    single {
        Circuit.Builder()

            .addPresenter<GenkaikorScreen, GenkaikorScreen.State>(GenkaikorPresenter())
            .addUi<GenkaikorScreen, GenkaikorScreen.State> { state, modifier ->
                Genkaikor(state, modifier)
            }

            .build()
    }
}
