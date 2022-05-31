package com.example.myanimelist.di

import com.example.myanimelist.filters.edition.EditFilters
import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.AnimeList
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
import com.example.myanimelist.service.img.IImgStorage
import com.example.myanimelist.service.img.ImgStorage
import com.example.myanimelist.utils.html.HTMLGenerator
import javafx.scene.chart.PieChart.Data
import org.koin.dsl.module

val dataBaseManagerModule = module {
    single { DataBaseManager("anime.db") }
}
val dataBaseManagerModuleDev = module {
    single { DataBaseManager("animeDev.db") }
}

val usersRepositoryModule = module {
    single { DataBaseManager("anime.db") }
    single<IUsersRepository> { UsersRepository(get()) }
}
val animeRepositoryModule = module {
    single { DataBaseManager("anime.db") }
    single<IAnimeRepository> { AnimeRepository(get()) }
}

val animeListRepositoryModule = module {
    single { DataBaseManager("anime.db") }
    single { UsersRepository(get()) }
    single<IRepositoryAnimeList> { AnimeListRepository(get(), get()) }
}

val reviewsRepositoryModule = module {
    single { DataBaseManager("anime.db") }
    single { AnimeRepository(get()) }
    single { UsersRepository(get()) }
    single<IRepositoryReview> { ReviewsRepository(get(), get(), get()) }
}

val reviewsRepositoryModuleDev = module {
    single { DataBaseManager("animeDev.db") }
    single { AnimeRepository(get()) }
    single { UsersRepository(get()) }
    single<IRepositoryReview> { ReviewsRepository(get(), get(), get()) }
}

val imgStorageModule = module {
    single<IImgStorage> { ImgStorage() }
    single { ImgStorage() }
}

val htmlGeneratorModule = module {
    single { HTMLGenerator() }
}

val animeStorageModule = module {
    single<IAnimeStorage> { AnimeStorage() }
}

val editFiltersModule = module {
    single{ EditFilters() }
}