package com.example.myanimelist.repositories.animes

import com.example.myanimelist.modules.repositoryModule
import com.example.myanimelist.utilities.DataDB
import com.example.myanimelist.utilities.DataDB.getTestingAnime
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.test.inject
import org.koin.test.junit5.AutoCloseKoinTest
import java.util.*
import kotlin.test.assertEquals

internal class AnimeRepositoryTest : AutoCloseKoinTest() {

    private val repo by inject<IAnimeRepository>()

    init {
        startKoin { modules(repositoryModule) }
    }

    @AfterEach
    internal fun deleteAll() = DataDB.deleteAll("Animes")


    @Test
    fun findById() {
        val anime = getTestingAnime()
        repo.add(anime)

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
        val anime2 = getTestingAnime()
        repo.add(anime1)
        repo.add(anime2)

        val animeListTest = repo.findAll().toList()

        assert(animeListTest.containsAll(listOf(anime1, anime2)))
    }

    @Test
    fun update() {
        val anime = getTestingAnime()
        repo.add(anime)

        val response = repo.update(anime.also { it.title = "AAA" })

        val animeReceived = repo.findById(anime.id)
        assertEquals(animeReceived?.title, response?.title)
    }

    @Test
    fun add() {
        val newAnime = getTestingAnime()

        val response = repo.add(newAnime)
        assertEquals(response, newAnime)
    }

    @Test
    fun delete() {
        val anime = getTestingAnime()

        repo.delete(anime.id)

        val animeReceived = repo.findById(anime.id)
        assertTrue(animeReceived == null)
    }
}