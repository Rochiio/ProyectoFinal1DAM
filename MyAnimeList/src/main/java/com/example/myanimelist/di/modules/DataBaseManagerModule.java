package com.example.myanimelist.di.modules;


import com.example.myanimelist.manager.DataBaseManager;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DataBaseManagerModule {
    @Singleton
    @Provides
    DataBaseManager provideDB(){
        return DataBaseManager.getInstance();
    }
}
