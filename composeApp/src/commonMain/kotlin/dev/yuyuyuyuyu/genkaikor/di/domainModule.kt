package dev.yuyuyuyuyu.genkaikor.di

import dev.yuyuyuyuyu.genkaikor.domain.useCase.InsertDakutenUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::InsertDakutenUseCase)
}
