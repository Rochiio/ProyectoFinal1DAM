package com.example.myanimelist.services

import com.example.myanimelist.di.servicesModule
import com.example.myanimelist.dto.AnimeDTO
import com.example.myanimelist.service.anime.IAnimeStorage
import com.example.myanimelist.utilities.getTestingAnime
import com.example.myanimelist.utils.Properties
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.test.inject
import org.koin.test.junit5.ClosingKoinTest
import java.io.FileReader

class AnimeStorageTest : ClosingKoinTest {
    private val storage: IAnimeStorage by inject()


    init {
        startKoin {
            modules(
                servicesModule
            )
        }
    }


    @Test
    @Order(0)
    fun save() {
        var csv: String
        val listAnimes = listOf(AnimeDTO(getTestingAnime()))
        storage.save(listAnimes)

        FileReader(Properties.ANIME_SAVE).use {
            csv = it.readLines()[1]
        }

        assert(csv.isNotBlank())
    }

    @Test
    @Order(1)
    fun load() {
        val animeList: List<AnimeDTO>? = storage.load()


        assert(animeList?.isNotEmpty() ?: false)
    }

}