package com.example.myanimelist.di.modules;


import com.example.myanimelist.filters.login.LoginFilters;
import com.example.myanimelist.filters.login.RegisterFilters;
import com.example.myanimelist.repositories.users.IUsersRepository;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(includes = RepositoriesModule.class)
public class FiltersModule {
    @Singleton
    @Provides
    public LoginFilters loginFilters(IUsersRepository repository) {
        return new LoginFilters(repository);
    }

    @Singleton
    @Provides
    public RegisterFilters registerFilters(IUsersRepository repository) {
        return new RegisterFilters(repository);
    }


}
