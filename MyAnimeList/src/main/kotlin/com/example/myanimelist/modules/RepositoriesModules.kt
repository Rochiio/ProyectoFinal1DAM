package com.example.myanimelist.modules

import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.repositories.admins.AdminRepository
import com.example.myanimelist.repositories.admins.IAdminRepository
import com.example.myanimelist.repositories.animes.AnimeRepository
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.repositories.reviews.IRepositoryReview
import com.example.myanimelist.repositories.reviews.ReviewsRepository
import com.example.myanimelist.repositories.usersRepositories.IUsersRepository
import com.example.myanimelist.repositories.usersRepositories.UsersRepository
import org.koin.dsl.module

object RepositoriesModules {
    val repositoryModule = module {
        single { DataBaseManager() }
        single<IUsersRepository> { UsersRepository(get()) }
        single<IAnimeRepository> { AnimeRepository(get()) }
        single<IAdminRepository> { AdminRepository(get()) }
        single<IRepositoryReview> { ReviewsRepository(get()) }
    }
}