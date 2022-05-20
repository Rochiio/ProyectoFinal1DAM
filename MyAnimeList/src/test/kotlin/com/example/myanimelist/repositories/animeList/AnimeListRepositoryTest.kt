package com.example.myanimelist.repositories.animeList

import com.example.myanimelist.extensions.execute
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.utilities.getTestingAnime
import com.example.myanimelist.utilities.getTestingUser
import com.example.myanimelist.utils.Properties
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class AnimeListRepositoryTest {
    private val user = getTestingUser()
    private val anime = getTestingAnime()
    private var animeListRepository: IRepositoryAnimeList = DependenciesManager.getAnimeListRepo()

    @BeforeEach
    fun deleteAll() {
        DependenciesManager.getDatabaseManager().execute {
            initData(Properties.SCRIPT_FILE_DATABASE, true)
        }
    }

    @Test
    fun add() {
        animeListRepository.add(anime, user)
        val list = animeListRepository.findByUserId(user)

        assertTrue(list.map { it.id }.contains(anime.id))
    }

    @Test
    fun delete() {
        animeListRepository.add(anime, user)
        animeListRepository.delete(anime, user)
        val list = animeListRepository.findByUserId(user)

        assertTrue(list.isEmpty())
    }

    @Test
    fun findByUserId() {
        animeListRepository.add(anime, user)
        val list = animeListRepository.findByUserId(user)
        assertTrue(list.map { it.id }.contains(anime.id))
    }
}