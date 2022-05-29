package com.example.myanimelist.di

import com.example.myanimelist.managers.DataBaseManager
import dagger.*
import javax.inject.Singleton

@Module
interface DatabaseModule {
    @Singleton
    @Provides
    fun databaseProvides() = DataBaseManager("anime.db")

}