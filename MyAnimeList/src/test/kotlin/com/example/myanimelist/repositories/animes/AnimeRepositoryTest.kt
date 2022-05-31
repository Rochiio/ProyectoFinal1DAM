package com.example.myanimelist.repositories.animes

import com.example.myanimelist.di.animeRepositoryModule
import com.example.myanimelist.di.dataBaseManagerModuleDev
import com.example.myanimelist.di.reviewsRepositoryModuleDev
import com.example.myanimelist.di.usersRepositoryModule
import com.example.myanimelist.extensions.execute
import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.utilities.*
import com.example.myanimelist.utils.Properties
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.get
import java.util.*
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class AnimeRepositoryTest :KoinTest{
    private lateinit var repo: IAnimeRepository

    @BeforeAll
    fun start() {
        startKoin {
            modules(
                dataBaseManagerModuleDev,
                animeRepositoryModule
            )
        }
        repo = get()

    }

    @BeforeEach
    fun setUp() {

        val db: DataBaseManager = get<DataBaseManager>()
        db.execute {
            initData(Properties.SCRIPT_FILE_DATABASE, false)
        }
    }


    @Test
    fun findById() {
        val anime = getTestingAnime()

        val animeTest = repo.findById(anime.id)

        assertEquals(animeTest, anime)
    }

    @Test
    fun findByIdNotPresent() {
        val animeTest = repo.findById(UUID.randomUUID())
        assert(animeTest == null)
    }

    @Test
    fun findAll() {
        val anime1 = getTestingAnime()

        val animeListTest = repo.findAll().toList()

        assert(animeListTest.contains(anime1))
    }

    @Test
    fun update() {
        val anime = getTestingAnimeUpdate()

        val response = repo.update(anime.also { it.title = "AAA" })

        assertNotNull(response)
    }

    @Test
    fun add() {
        val newAnime = getNewTestingAnime()

        val response = repo.add(newAnime)
        assertEquals(response, newAnime)
    }

    @Test
    fun delete() {
        val anime = getTestingAnimeDelete()
        repo.delete(anime.id)
        assertNull(repo.findById(anime.id))
    }
}