package com.example.myanimelist.di.components;

import com.example.myanimelist.di.modules.RepositoriesModule;
import com.example.myanimelist.manager.DataBaseManager;
import com.example.myanimelist.repositories.admins.IAdminRepository;
import com.example.myanimelist.repositories.animes.IAnimeRepository;
import com.example.myanimelist.repositories.reviews.IRepositoryReview;
import com.example.myanimelist.repositories.users.IUsersRepository;
import dagger.Component;

import javax.inject.Singleton;

@Component(modules = {RepositoriesModule.class})
@Singleton
public interface RepositoriesComponent {
    IUsersRepository buildUserRepo();

    IAnimeRepository buildAnimeRepo();

    IAdminRepository buildAdminRepo();

    IRepositoryReview buildReviewRepo();

    DataBaseManager buildDatabaseManager();
}