package com.example.myanimelist.repositories.animeList

import com.example.myanimelist.di.repositoriesModule
import com.example.myanimelist.extensions.execute
import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.utilities.getTestingAnime
import com.example.myanimelist.utilities.getTestingAnimeUpdate
import com.example.myanimelist.utilities.getTestingUser
import com.example.myanimelist.utilities.getTestingUserUpdate
import com.example.myanimelist.utils.Properties
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.test.get
import org.koin.test.inject
import org.koin.test.junit5.ClosingKoinTest

internal class AnimeListRepositoryTest : ClosingKoinTest {
    private val animeListRepository: IRepositoryAnimeList by inject()


    init {
        startKoin {
            modules(
                repositoriesModule
            )
        }
    }

    @BeforeEach
    fun setUp() {

        val db: DataBaseManager = get()
        db.execute {
            initData(Properties.SCRIPT_FILE_DATABASE, false)
        }
    }

    @Test
    fun add() {
        val user = getTestingUser()
        val anime = getTestingAnime()
        val animeAdded = animeListRepository.add(anime, user)
        assertNotNull(animeAdded)
    }

    @Test
    fun delete() {
        val user = getTestingUser()
        val anime = getTestingAnime()
        animeListRepository.delete(anime, user)
        val list = animeListRepository.findByUserId(user)
        assert(!list.map { it.id }.contains(anime.id))
    }

    @Test
    fun findByUserId() {
        val user = getTestingUserUpdate()
        val anime = getTestingAnimeUpdate()
        val list = animeListRepository.findByUserId(user)
        assert(list.map { it.id }.contains(anime.id))
    }
}