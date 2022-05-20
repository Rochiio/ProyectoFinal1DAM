package com.example.myanimelist.repositories.animes

import com.example.myanimelist.extensions.execute
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.managers.DependenciesManager.getAnimesRepo
import com.example.myanimelist.utilities.getTestingAnime
import com.example.myanimelist.utils.Properties
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

internal class AnimeRepositoryTest {


    private val repo = getAnimesRepo()

    @BeforeEach
    fun deleteAll() {
        DependenciesManager.getDatabaseManager().execute {
            initData(Properties.SCRIPT_FILE_DATABASE, true)
        }
    }


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