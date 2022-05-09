package com.example.myanimelist.repositories.reviews

import com.example.myanimelist.exceptions.RepositoryException
import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.Review
import com.example.myanimelist.models.User
import com.example.myanimelist.modules.RepositoriesModules.repositoryModule
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.utilities.DataDB
import com.example.myanimelist.utilities.DataDB.getTestingAnime
import com.example.myanimelist.utilities.DataDB.getTestingUser
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.test.inject
import org.koin.test.junit5.AutoCloseKoinTest
import java.util.*

internal class ReviewsRepositoryTest : AutoCloseKoinTest() {
    private val reviewsRepository by inject<IRepositoryReview>()
    private val usersRepository by inject<IUsersRepository>()
    private val animeRepository by inject<IAnimeRepository>()

    private val user: User
    private val anime: Anime

    init {
        startKoin { modules(repositoryModule) }
        user =
            usersRepository.add(getTestingUser()) ?: throw RepositoryException("Couldn't load user for Reviews tests")
        anime =
            animeRepository.add(getTestingAnime()) ?: throw RepositoryException("Couldn't load anime for Reviews tests")
    }

    @AfterEach
    fun deleteAll() = DataDB.deleteAll("Reviews")


    private var reviewTest =
        Review(UUID.randomUUID(), anime, user, 0, "Me ha gustado")

    @Test
    fun addReview() {
        val result = reviewsRepository.addReview(reviewTest)
        assertEquals(result, reviewTest)
    }

    @Test
    fun showReviewsAnime() {
        reviewsRepository.addReview(reviewTest)
        val listResult = reviewsRepository.showReviewsAnime(reviewTest.anime.id)
        assertEquals(listResult[0], reviewTest)
    }

    @Test
    fun addScore() {
        reviewTest.comment = null.toString()
        reviewTest.score = 2

        val result = reviewsRepository.addReview(reviewTest)
        assertEquals(result, reviewTest)
    }
}