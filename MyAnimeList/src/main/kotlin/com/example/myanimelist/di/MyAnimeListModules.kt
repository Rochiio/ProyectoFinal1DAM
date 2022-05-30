package com.example.myanimelist.di

import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.AnimeList
import com.example.myanimelist.repositories.animeList.AnimeListRepository
import com.example.myanimelist.repositories.animes.AnimeRepository
import com.example.myanimelist.repositories.reviews.ReviewsRepository
import com.example.myanimelist.repositories.users.UsersRepository
import com.example.myanimelist.service.img.ImgStorage
import com.example.myanimelist.utils.html.HTMLGenerator
import javafx.scene.chart.PieChart.Data
import org.koin.dsl.module

val dataBaseManagerModule = module {
    single { DataBaseManager("anime.db") }
}

val usersRepositoryModule = module {
    single { DataBaseManager("anime.db") }
    single { UsersRepository(get()) }
}

val animeRepositoryModule = module{
    single { DataBaseManager("anime.db") }
    single { AnimeRepository(get()) }
}

val animeListRepositoryModule = module {
    single { DataBaseManager("anime.db") }
    single {UsersRepository(get())}
    single { AnimeListRepository(get(), get()) }
}

val reviewsRepositoryModule = module{
    single { DataBaseManager("anime.db") }
    single { AnimeRepository(get()) }
    single {UsersRepository(get())}
    single { ReviewsRepository(get(), get(), get()) }
}

val imgStorageModule = module{
    single { ImgStorage() }
}

val htmlGeneratorModule = module{
    single{HTMLGenerator()}
}