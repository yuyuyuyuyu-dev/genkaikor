package dev.yuyuyuyuyu.genkaikor.di

import dev.yuyuyuyuyu.genkaikor.ui.genkaikor.GenkaikorViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule =
    module {
        viewModelOf(::GenkaikorViewModel)
    }
