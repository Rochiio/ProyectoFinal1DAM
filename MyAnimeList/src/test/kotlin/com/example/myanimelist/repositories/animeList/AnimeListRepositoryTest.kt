package com.example.myanimelist.repositories.animeList

import com.example.myanimelist.di.*
import com.example.myanimelist.extensions.execute
import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.utilities.*
import com.example.myanimelist.utils.Properties
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.get

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class AnimeListRepositoryTest : KoinTest{

    private lateinit var animeListRepository: IRepositoryAnimeList

    @BeforeAll
    fun start() {
        startKoin {
            modules(
                dataBaseManagerModuleDev,
                animeListRepositoryModule,
                usersRepositoryModule,
                animeRepositoryModule
            )
        }
        animeListRepository = get()
    }

    @BeforeEach
    fun setUp() {

        val db: DataBaseManager = get<DataBaseManager>()
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