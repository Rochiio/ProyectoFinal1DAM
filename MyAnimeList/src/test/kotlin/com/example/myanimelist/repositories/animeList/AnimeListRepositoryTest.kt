package com.example.myanimelist.repositories.animeList

import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.utilities.DataDB
import com.example.myanimelist.utilities.DataDB.getTestingAnime
import com.example.myanimelist.utilities.DataDB.getTestingUser
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class AnimeListRepositoryTest {

    private val user = getTestingUser()
    private val anime = getTestingAnime()
    private var animeListRepository : AnimeListRepository = AnimeListRepository(DependenciesManager.getDatabaseManager(),DependenciesManager.getLogger(AnimeListRepository::class.java))

    @AfterEach
    fun deleteAll() {
        DataDB.deleteAll("usuarios")
        DataDB.deleteAll("animeLists")
    }
    @Test
    fun add() {

        val animeAdded = animeListRepository.add(anime, user)
        val list = animeListRepository.findByUserId(user)

        assertTrue(list!!.contains(anime.id))
    }

    @Test
    fun delete() {

        animeListRepository.add(anime,user)
        animeListRepository.delete(anime, user)
        val list = animeListRepository.findByUserId(user)

        assertTrue(list!!.isEmpty())
    }

    @Test
    fun findByUserId(){
        animeListRepository.add(anime, user)
        val list = animeListRepository.findByUserId(user)
        assertTrue(list!!.contains(anime.id))

    }
}