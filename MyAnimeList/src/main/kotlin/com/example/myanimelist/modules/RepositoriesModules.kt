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
import com.example.myanimelist.service.anime.AnimeStorage
import com.example.myanimelist.service.anime.IAnimeStorage
import com.example.myanimelist.service.backup.BackupStorage
import com.example.myanimelist.service.backup.IBackupStorage
import org.koin.dsl.module
import java.util.logging.LogManager


val repositoryModule = module {
    single { DataBaseManager() }
    single<IUsersRepository> {
        UsersRepository(
            get(),
            LogManager.getLogManager().getLogger("users.repository") ?: throw Exception("Logger Not found")
        )
    }
    single<IAnimeRepository> {
        AnimeRepository(
            get(),
            LogManager.getLogManager().getLogger("anime.repository") ?: throw Exception("Logger Not found")
        )
    }
    single<IAdminRepository> {
        AdminRepository(
            get(),
            LogManager.getLogManager().getLogger("admin.repository") ?: throw Exception("Logger Not found")
        )
    }
    single<IRepositoryReview> {
        ReviewsRepository(
            get(),
            get(),
            get(),
            LogManager.getLogManager().getLogger("reviews.repository") ?: throw Exception("Logger Not found")
        )
    }
    single<IBackupStorage> { BackupStorage() }
    single<IAnimeStorage> { AnimeStorage() }
}
