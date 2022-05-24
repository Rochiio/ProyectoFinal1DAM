package com.example.myanimelist.repositories.animes

import com.example.myanimelist.managers.DependenciesManager.getAnimesRepo
import com.example.myanimelist.utilities.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

internal class AnimeRepositoryTest {
    private val repo = getAnimesRepo()

    @BeforeEach
    fun setUp() = resetDb()


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