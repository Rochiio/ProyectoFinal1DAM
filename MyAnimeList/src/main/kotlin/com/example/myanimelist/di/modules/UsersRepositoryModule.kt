package com.example.myanimelist.di

import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.repositories.users.UsersRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface UsersRepositoryModule {
    @Singleton
    @Binds
    fun bindUsersRepository(repository: UsersRepository) : IUsersRepository
}