package com.example.myanimelist.repositories.reviews

import com.example.myanimelist.models.Reviews
import com.example.myanimelist.modules.RepositoriesModules.repositoryModule
import com.example.myanimelist.utilities.DataDB
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.test.inject
import org.koin.test.junit5.AutoCloseKoinTest
import java.util.*

internal class ReviewsRepositoryTest : AutoCloseKoinTest() {
    private val repository by inject<IRepositoryReview>()

    init {
        startKoin { modules(repositoryModule) }
    }

    private var reviewTest: Reviews =
        Reviews(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), 0, "Me ha gustado")

    @AfterEach
    fun setUp() {
        DataDB.deleteAllReviews()
    }

    @Test
    fun addReview() {
        val result = repository.addReview(reviewTest)
        assertAll(
            { assertEquals(result.id, reviewTest.id) },
            { assertEquals(result.idAnime, reviewTest.idAnime) },
            { assertEquals(result.idUser, reviewTest.idUser) },
            { assertEquals(result.score, reviewTest.score) },
            { assertEquals(result.comment, reviewTest.comment) }
        )
    }

    @Test
    fun showReviewsAnime() {
        repository.addReview(reviewTest)
        val listResult = repository.showReviewsAnime(reviewTest.idAnime)
        assertAll(
            { assertEquals(listResult.size, 1) },
            { assertEquals(listResult[0].id, reviewTest.id) },
            { assertEquals(listResult[0].idAnime, reviewTest.idAnime) },
            { assertEquals(listResult[0].idUser, reviewTest.idUser) },
            { assertEquals(listResult[0].score, reviewTest.score) },
            { assertEquals(listResult[0].comment, reviewTest.comment) }
        )
    }

    @Test
    fun addScore() {
        reviewTest.comment = null.toString()
        reviewTest.score = 2

        val result = repository.addReview(reviewTest)
        assertAll(
            { assertEquals(result.id, reviewTest.id) },
            { assertEquals(result.idAnime, reviewTest.idAnime) },
            { assertEquals(result.idUser, reviewTest.idUser) },
            { assertEquals(result.score, reviewTest.score) },
            { assertEquals(result.comment, reviewTest.comment) }
        )
    }
}