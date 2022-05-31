package com.example.myanimelist.repositories.animes

import com.example.myanimelist.di.repositoriesModule
import com.example.myanimelist.extensions.execute
import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.utilities.getNewTestingAnime
import com.example.myanimelist.utilities.getTestingAnime
import com.example.myanimelist.utilities.getTestingAnimeDelete
import com.example.myanimelist.utilities.getTestingAnimeUpdate
import com.example.myanimelist.utils.Properties
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.test.get
import org.koin.test.inject
import org.koin.test.junit5.ClosingKoinTest
import java.util.*

internal class AnimeRepositoryTest : ClosingKoinTest {
    private val repo: IAnimeRepository by inject()


    init {
        startKoin {
            modules(repositoriesModule)
        }
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