package com.example.myanimelist.repositories.reviews

import com.example.myanimelist.di.repositoriesModule
import com.example.myanimelist.extensions.execute
import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.utilities.getNewTestingReview
import com.example.myanimelist.utilities.getTestingReview
import com.example.myanimelist.utils.Properties
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.test.get
import org.koin.test.inject
import org.koin.test.junit5.ClosingKoinTest

internal class ReviewsRepositoryTest : ClosingKoinTest {
    private val reviewsRepository: IRepositoryReview by inject()


    init {
        startKoin {
            modules(
                repositoriesModule
            )
        }
    }

    @BeforeEach
    fun setUp() {

        val db: DataBaseManager = get<DataBaseManager>()
        db.execute {
            initData(Properties.SCRIPT_FILE_DATABASE, false)
        }
    }

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