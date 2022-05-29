package com.example.myanimelist.di

import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.repositories.users.UsersRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(DatabaseModule::class))
interface UserRepositoryComponent {
    fun build(repository: UsersRepository): IUsersRepository
}