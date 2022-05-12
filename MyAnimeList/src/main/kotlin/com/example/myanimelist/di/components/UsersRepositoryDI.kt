package com.example.myanimelist.di.components

import com.example.myanimelist.controllers.UsersController
import com.example.myanimelist.di.modules.DataBaseManagerModule
import com.example.myanimelist.repositories.users.UsersRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataBaseManagerModule::class])
interface UsersRepositoryDI {
    fun build() : UsersRepository
    fun inject(controller : UsersController)
}