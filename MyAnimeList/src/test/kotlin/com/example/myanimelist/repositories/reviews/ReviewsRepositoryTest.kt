package com.example.myanimelist.repositories.reviews

import com.example.myanimelist.exceptions.RepositoryException
import com.example.myanimelist.manager.DataBaseManager
import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.Review
import com.example.myanimelist.models.User
import com.example.myanimelist.modules.repositoryModule
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.utilities.DataDB.getTestingAnime
import com.example.myanimelist.utilities.DataDB.getTestingUser
import com.example.myanimelist.utils.Properties.SCRIPT_FILE_DATABASE
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.test.inject
import org.koin.test.junit5.AutoCloseKoinTest

internal class ReviewsRepositoryTest : AutoCloseKoinTest() {
    private val reviewsRepository by inject<IRepositoryReview>()
    private val usersRepository by inject<IUsersRepository>()
    private val animeRepository by inject<IAnimeRepository>()

    private val db by inject<DataBaseManager>()
    private val user: User
    private val anime: Anime

    init {
        startKoin { modules(repositoryModule) }
        user =
            usersRepository.add(getTestingUser()) ?: throw RepositoryException("Couldn't load user for Reviews tests")
        anime =
            animeRepository.add(getTestingAnime()) ?: throw RepositoryException("Couldn't load anime for Reviews tests")
    }

    @BeforeEach
    fun setUp() = db.initData(SCRIPT_FILE_DATABASE,false)

    var reviewTest = Review(anime, user, 0, "Me ha gustado")


    @Test
    fun addReview() {
        val result = reviewsRepository.add(reviewTest)
        assertEquals(result, reviewTest)
    }

    @Test
    fun showReviewsAnime() {
        val listResult = reviewsRepository.findByAnimeId(reviewTest.anime.id).toList()
        assertEquals(listResult[0], reviewTest)
    }

    @Test
    fun showAllReviews() {
        reviewsRepository.add(reviewTest)
        val listResult = reviewsRepository.findAll().toList()
        assert(listResult.contains(reviewTest))
    }
}