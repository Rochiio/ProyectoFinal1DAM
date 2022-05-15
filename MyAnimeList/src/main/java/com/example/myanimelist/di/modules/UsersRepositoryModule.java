package com.example.myanimelist.di.modules;

import com.example.myanimelist.repositories.users.IUsersRepository;
import com.example.myanimelist.repositories.users.UsersRepository;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
@Module
public interface UsersRepositoryModule {
    @Singleton
    @Binds
    IUsersRepository bindUserRepository(UsersRepository repository);
}
