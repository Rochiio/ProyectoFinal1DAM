package com.example.myanimelist.repositories.reviews

import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.Reviews
import com.example.myanimelist.utilities.DataDB
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import java.util.UUID

internal class ReviewsRepositoryTest {
    var repository = ReviewsRepository(DataBaseManager.getInstance())
    var reviewTest: Reviews = Reviews(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID(),0,"Me ha gustado")

    @AfterEach
    fun setUp(){
        DataDB.deleteAllReviews()
    }

    @Test
    fun addReview() {
        val result = repository.addReview(reviewTest)
        assertAll(
            { assertEquals(result.id, reviewTest.id)},
            { assertEquals(result.idAnime, reviewTest.idAnime)},
            { assertEquals(result.idUser, reviewTest.idUser)},
            { assertEquals(result.score, reviewTest.score)},
            { assertEquals(result.comment, reviewTest.comment)}
        )
    }

    @Test
    fun showReviewsAnime() {
        repository.addReview(reviewTest)
        val listResult = repository.showReviewsAnime(reviewTest.idAnime.toString())
        assertAll(
            {assertEquals(listResult.size,1)},
            { assertEquals(listResult[0].id, reviewTest.id)},
            { assertEquals(listResult[0].idAnime, reviewTest.idAnime)},
            { assertEquals(listResult[0].idUser, reviewTest.idUser)},
            { assertEquals(listResult[0].score, reviewTest.score)},
            { assertEquals(listResult[0].comment, reviewTest.comment)}
        )
    }

    @Test
    fun addScore() {
        reviewTest.comment= null.toString()
        reviewTest.score=2

        val result = repository.addReview(reviewTest)
        assertAll(
            { assertEquals(result.id, reviewTest.id)},
            { assertEquals(result.idAnime, reviewTest.idAnime)},
            { assertEquals(result.idUser, reviewTest.idUser)},
            { assertEquals(result.score, reviewTest.score)},
            { assertEquals(result.comment, reviewTest.comment)}
        )
    }
}