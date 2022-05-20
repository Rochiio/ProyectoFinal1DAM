package com.example.myanimelist.repositories.reviews

import com.example.myanimelist.extensions.execute
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.managers.DependenciesManager.getAnimesRepo
import com.example.myanimelist.managers.DependenciesManager.getReviewsRepo
import com.example.myanimelist.managers.DependenciesManager.getUsersRepo
import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.Review
import com.example.myanimelist.models.User
import com.example.myanimelist.utilities.getTestingAnime
import com.example.myanimelist.utilities.getTestingUser
import com.example.myanimelist.utils.Properties
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


internal class ReviewsRepositoryTest {

    private val usersRepository = getUsersRepo()
    private val animeRepository = getAnimesRepo()
    private val reviewsRepository = getReviewsRepo()

    private var user: User = usersRepository.add(getTestingUser()) ?: throw Exception()
    private var anime: Anime = animeRepository.add(getTestingAnime()) ?: throw Exception()

    @BeforeEach
    fun deleteAll() {
        DependenciesManager.getDatabaseManager().execute {
            initData(Properties.SCRIPT_FILE_DATABASE, true)
        }
    }


    private var reviewTest =
        Review(anime, user, 0, "Me ha gustado")

    @Test
    fun addReview() {
        val result = reviewsRepository.add(reviewTest)
        assertEquals(result, reviewTest)
    }

    @Test
    fun showReviewsAnime() {
        reviewsRepository.add(reviewTest)
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