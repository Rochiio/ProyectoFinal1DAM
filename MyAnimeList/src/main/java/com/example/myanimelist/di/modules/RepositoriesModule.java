package com.example.myanimelist.di.modules;

import com.example.myanimelist.manager.DataBaseManager;
import com.example.myanimelist.repositories.admins.AdminRepository;
import com.example.myanimelist.repositories.admins.IAdminRepository;
import com.example.myanimelist.repositories.animes.AnimeRepository;
import com.example.myanimelist.repositories.animes.IAnimeRepository;
import com.example.myanimelist.repositories.reviews.IRepositoryReview;
import com.example.myanimelist.repositories.reviews.ReviewsRepository;
import com.example.myanimelist.repositories.users.IUsersRepository;
import com.example.myanimelist.repositories.users.UsersRepository;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class RepositoriesModule {
    @Singleton
    @Provides
    IAnimeRepository animeRepository(DataBaseManager dataBaseManager) {
        return new AnimeRepository(dataBaseManager);
    }

    @Singleton
    @Provides
    IUsersRepository usersRepository(DataBaseManager dataBaseManager) {
        return new UsersRepository(dataBaseManager);
    }

    @Singleton
    @Provides
    IRepositoryReview reviewsRepository(IUsersRepository usersRepository, IAnimeRepository animeRepository, DataBaseManager dataBaseManager) {
        return new ReviewsRepository(dataBaseManager, animeRepository, usersRepository);
    }

    @Singleton
    @Provides
    IAdminRepository adminsRepository(DataBaseManager dataBaseManager) {
        return new AdminRepository(dataBaseManager);
    }

    @Singleton
    @Provides
    DataBaseManager databaseManager() {
        return new DataBaseManager();
    }
}