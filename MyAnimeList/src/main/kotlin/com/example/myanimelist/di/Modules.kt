package com.example.myanimelist.di

import com.example.myanimelist.adapters.LocalDateTypeAdapter
import com.example.myanimelist.filters.edition.EditFilters
import com.example.myanimelist.filters.login.LoginFilters
import com.example.myanimelist.filters.login.RegisterFilters
import com.example.myanimelist.managers.CurrentUser
import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.repositories.animeList.AnimeListRepository
import com.example.myanimelist.repositories.animeList.IRepositoryAnimeList
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
import com.example.myanimelist.service.img.IImgStorage
import com.example.myanimelist.service.img.ImgStorage
import com.example.myanimelist.utils.html.GeneratorHtml
import com.example.myanimelist.utils.html.GeneratorHtmlStats
import com.google.gson.GsonBuilder
import org.koin.dsl.module
import java.time.LocalDate


var isTesting = false;
fun getDb(): String = if (isTesting) "animeDev.db" else "anime.db"

val dataBaseManagerModule = module {
    single { DataBaseManager(getDb()) }
}

val gsonModule = module {
    single {
        GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter()).create()
    }
}

val repositoriesModule = module {
    includes(dataBaseManagerModule)
    single<IUsersRepository> { UsersRepository(get()) }
    single<IAnimeRepository> { AnimeRepository(get()) }
    single<IRepositoryAnimeList> { AnimeListRepository(get(), get()) }
    single<IRepositoryReview> { ReviewsRepository(get(), get(), get()) }
}

val servicesModule = module {
    includes(gsonModule)
    single<IImgStorage> { ImgStorage() }
    single<IAnimeStorage> { AnimeStorage() }
    single<IBackupStorage> { BackupStorage(get()) }
    single<GeneratorHtml> { GeneratorHtmlStats() }
}

val filtersModule = module {
    includes(repositoriesModule)
    single { EditFilters() }
    single { LoginFilters(get()) }
    single { RegisterFilters(get()) }
}

val userModule = module {
    single { CurrentUser() }
}