package com.example.myanimelist.di.components;

import com.example.myanimelist.controllers.inicio.LoginController;
import com.example.myanimelist.controllers.inicio.RegisterController;
import com.example.myanimelist.di.modules.FiltersModule;
import com.example.myanimelist.filters.login.LoginFilters;
import com.example.myanimelist.filters.login.RegisterFilters;
import dagger.Component;

import javax.inject.Singleton;

@Component(modules = {FiltersModule.class})
@Singleton
public interface ControllersComponent {
    LoginFilters getLoginFilters();

    RegisterFilters getRegisterFilters();


    void inject(RegisterController controller);

    void inject(LoginController controller);
}