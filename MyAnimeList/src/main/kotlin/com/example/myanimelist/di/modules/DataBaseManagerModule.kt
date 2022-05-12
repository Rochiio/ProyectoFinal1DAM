package com.example.myanimelist.di.modules
import com.example.myanimelist.manager.DataBaseManager
import dagger.*
import javax.inject.Singleton

@Module
class DataBaseManagerModule {

    @Singleton
    @Provides
    fun provideDB() : DataBaseManager{
        return DataBaseManager.getInstance();
    }
}