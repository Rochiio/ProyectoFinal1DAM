package com.example.myanimelist.modules

import com.example.myanimelist.manager.DataBaseManager
import com.example.myanimelist.repositories.admins.AdminRepository
import com.example.myanimelist.repositories.admins.IAdminRepository
import com.example.myanimelist.repositories.animes.AnimeRepository
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.repositories.reviews.IRepositoryReview
import com.example.myanimelist.repositories.reviews.ReviewsRepository
import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.repositories.users.UsersRepository
import org.apache.logging.log4j.LogManager
import org.koin.dsl.module


val repositoryModule = module {
    single { DataBaseManager() }
    single<IUsersRepository> {
        UsersRepository(
            get(),
            LogManager.getLogger(UsersRepository::class.java)
        )
    }
    single<IAnimeRepository> {
        AnimeRepository(
            get(),
            LogManager.getLogger(AnimeRepository::class.java)
        )
    }
    single<IAdminRepository> {
        AdminRepository(
            get(),
            LogManager.getLogger(AdminRepository::class.java)
        )
    }
    single<IRepositoryReview> {
        ReviewsRepository(
            get(),
            get(),
            get(),
            LogManager.getLogger(ReviewsRepository::class.java)
        )
    }
}
