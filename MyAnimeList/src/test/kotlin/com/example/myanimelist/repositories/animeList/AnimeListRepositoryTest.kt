package com.example.myanimelist.repositories.animeList

import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.repositories.RepoTest
import com.example.myanimelist.utilities.getTestingAnime
import com.example.myanimelist.utilities.getTestingAnimeUpdate
import com.example.myanimelist.utilities.getTestingUser
import com.example.myanimelist.utilities.getTestingUserUpdate
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

internal class AnimeListRepositoryTest : RepoTest() {

    private var animeListRepository: IRepositoryAnimeList = DependenciesManager.getAnimeListRepo()


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