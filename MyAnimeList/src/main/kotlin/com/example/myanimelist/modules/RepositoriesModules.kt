package com.example.myanimelist.modules

import com.example.myanimelist.filters.login.LoginFilters
import com.example.myanimelist.filters.login.RegisterFilters
import com.example.myanimelist.manager.DataBaseManager
import com.example.myanimelist.repositories.admins.AdminRepository
import com.example.myanimelist.repositories.admins.IAdminRepository
import com.example.myanimelist.repositories.animes.AnimeRepository
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.repositories.reviews.IRepositoryReview
import com.example.myanimelist.repositories.reviews.ReviewsRepository
import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.repositories.users.UsersRepository
import com.example.myanimelist.service.anime.AnimeStorage
import com.example.myanimelist.service.anime.IAnimeStorage
import com.example.myanimelist.service.backup.BackupStorage
import com.example.myanimelist.service.backup.IBackupStorage
import org.apache.logging.log4j.LogManager
import org.koin.dsl.module

/*
val repositoryModule = module {
    single { DataBaseManager() }
    single<IUsersRepository> {
        UsersRepository(
            get(),
            LogManager.getLogger(UsersRepository::class.java) ?: throw Exception("Logger Not found")
        )
    }
    single<IAnimeRepository> {
        AnimeRepository(
            get(),
            LogManager.getLogger(AnimeRepository::class.java) ?: throw Exception("Logger Not found")
        )
    }
    single<IAdminRepository> {
        AdminRepository(
            get(),
            LogManager.getLogger(AdminRepository::class.java) ?: throw Exception("Logger Not found")
        )
    }
    single<IRepositoryReview> {
        ReviewsRepository(
            get(),
            get(),
            get(),
            LogManager.getLogger(ReviewsRepository::class.java) ?: throw Exception("Logger Not found")
        )
    }
    single<IBackupStorage> { BackupStorage() }
    single<IAnimeStorage> { AnimeStorage() }
    single { LoginFilters(get()) }
    single { RegisterFilters(get()) }

}
*/