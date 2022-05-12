package com.example.myanimelist.repositories.reviews

import com.example.myanimelist.exceptions.RepositoryException
import com.example.myanimelist.manager.DataBaseManager
import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.Review
import com.example.myanimelist.models.User
//import com.example.myanimelist.modules.repositoryModule
import com.example.myanimelist.repositories.animes.AnimeRepository
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.repositories.users.UsersRepository
import com.example.myanimelist.utilities.DataDB
import com.example.myanimelist.utilities.DataDB.getTestingAnime
import com.example.myanimelist.utilities.DataDB.getTestingUser
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.koin.core.context.startKoin
import org.koin.test.inject
import org.koin.test.junit5.AutoCloseKoinTest

internal class ReviewsRepositoryTest : AutoCloseKoinTest() {
//    private val reviewsRepository by inject<IRepositoryReview>()


    private val usersRepository: UsersRepository = UsersRepository(DataBaseManager.getInstance())
    private val animeRepository: AnimeRepository = AnimeRepository(DataBaseManager.getInstance())
    private val reviewsRepository: ReviewsRepository = ReviewsRepository(
        DataBaseManager.getInstance(),
        animeRepository,
        usersRepository
    )
    private lateinit var user: User
    private lateinit var anime: Anime

    @BeforeAll
    fun init(){
        user = usersRepository.add(getTestingUser())!!
        anime = animeRepository.add(getTestingAnime())!!
    }
    @AfterEach
    fun deleteAll() = DataDB.deleteAll("Reviews")


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