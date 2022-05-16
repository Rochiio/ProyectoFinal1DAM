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

import com.example.myanimelist.utils.Properties.SCRIPT_FILE_DATABASE
import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.assertEquals


internal class ReviewsRepositoryTest {
//    private val reviewsRepository by inject<IRepositoryReview>()

    private val db: DataBaseManager
    private val usersRepository = UsersRepository(DataBaseManager.getInstance())
    private val animeRepository = AnimeRepository(DataBaseManager.getInstance())
    private val reviewsRepository = ReviewsRepository(DataBaseManager.getInstance(), animeRepository, usersRepository)

    private var user: User
    private var anime: Anime

    init{
        user = usersRepository.add(getTestingUser())!!
        anime = animeRepository.add(getTestingAnime())!!
        db = DataBaseManager.getInstance()
    }

    @BeforeEach
    fun setUp() {
        DataDB.deleteAll("Reviews")
    }

    val reviewTest = Review(anime, user, 0, "Me ha gustado")


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