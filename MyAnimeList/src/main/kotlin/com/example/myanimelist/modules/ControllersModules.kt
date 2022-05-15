package com.example.myanimelist.modules

import com.example.myanimelist.filters.login.LoginFilters
import com.example.myanimelist.filters.login.RegisterFilters
import org.koin.dsl.module

val controllerModules = module {
    single { LoginFilters(get()) }
    single { RegisterFilters(get()) }
}