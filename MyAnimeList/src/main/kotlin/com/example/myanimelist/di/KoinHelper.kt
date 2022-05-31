package com.example.myanimelist.di

import org.koin.core.context.startKoin

fun startAllModules() = startKoin {
    modules(
        repositoriesModule,
        servicesModule,
        filtersModule,
        userModule
    )
}