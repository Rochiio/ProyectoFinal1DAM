package com.example.myanimelist.repositories.reviews

import com.example.myanimelist.managers.DependenciesManager.getReviewsRepo
import com.example.myanimelist.repositories.RepoTest
import com.example.myanimelist.utilities.getNewTestingReview
import com.example.myanimelist.utilities.getTestingReview
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class ReviewsRepositoryTest : RepoTest() {
    private val reviewsRepository = getReviewsRepo()

    @Test
    fun addReview() {
        val reviewTest = getNewTestingReview()
        val result = reviewsRepository.add(reviewTest)
        assertEquals(result, reviewTest)
    }

    @Test
    fun showReviewsAnime() {
        val reviewTest = getTestingReview()
        val listResult = reviewsRepository.findByAnimeId(reviewTest.anime.id).toList()
        assert(listResult.contains(reviewTest) && listResult.count() == 1)
    }

    @Test
    fun showAllReviews() {
        val listResult = reviewsRepository.findAll().toList()
        assert(listResult.contains(getTestingReview()))
    }
}