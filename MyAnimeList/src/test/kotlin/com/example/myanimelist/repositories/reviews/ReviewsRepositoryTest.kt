package com.example.myanimelist.repositories.reviews

//import com.example.myanimelist.modules.repositoryModule
import com.example.myanimelist.di.components.DaggerRepositoriesComponent
import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.Review
import com.example.myanimelist.models.User
import com.example.myanimelist.utilities.DataDB
import com.example.myanimelist.utilities.DataDB.getTestingAnime
import com.example.myanimelist.utilities.DataDB.getTestingUser
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

internal class ReviewsRepositoryTest {
//    private val reviewsRepository by inject<IRepositoryReview>()

    private val usersRepository = DaggerRepositoriesComponent.create().buildUserRepo()
    private val animeRepository = DaggerRepositoriesComponent.create().buildAnimeRepo()
    private val reviewsRepository = DaggerRepositoriesComponent.create().buildReviewRepo()

    private lateinit var user: User
    private lateinit var anime: Anime

    @BeforeAll
    fun init() {
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