package com.example.myanimelist.controllers.main.user

import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.Review
import com.example.myanimelist.repositories.reviews.IRepositoryReview
import com.example.myanimelist.repositories.users.IUsersRepository
import javafx.fxml.FXML
import javafx.scene.control.Label
import org.apache.logging.log4j.Logger


class MainUserStatsController {

    //Generics
    val logger: Logger = DependenciesManager.getLogger<MainUserAnimeController>()
    val user = DependenciesManager.globalUser

    //FXML
    @FXML
    private lateinit var topTypeCount: Label

    @FXML
    private lateinit var topGenreCount: Label

    @FXML
    private lateinit var animeCount: Label

    @FXML
    private lateinit var botRatedAnime: Label

    @FXML
    private lateinit var topRatedAnime: Label

    private var userRepository: IUsersRepository = DependenciesManager.getUsersRepo()
    private var reviewRepository: IRepositoryReview = DependenciesManager.getReviewsRepo()
    private var myList: List<Anime> = emptyList()
    private var myReviews: List<Review> = emptyList()

    @FXML
    fun initialize() {

        myList = userRepository.getAnimeLists(user.id)
        myReviews = reviewRepository.findAll().filter { it.user.id == user.id }.toList()

        if (myList.isEmpty() || myReviews.isEmpty()) {
            val emptyMessage = "No hay animes y/o calificaciones suficientes..."
            animeCount.text = emptyMessage
            topGenreCount.text = emptyMessage
            topTypeCount.text = emptyMessage
            topRatedAnime.text = emptyMessage
            botRatedAnime.text = emptyMessage
            return
        }

        animeCount.text = myList.count().toString()
        topGenreCount.text = getTopGenre()
        topTypeCount.text = getMaxOccurences(myList.map { it.types }.toList())
        topRatedAnime.text = myReviews.maxByOrNull { it.score }?.anime?.title
        botRatedAnime.text = myReviews.minByOrNull { it.score }?.anime?.title
    }


    private fun getMaxOccurences(list: List<String>): String? {
        val ocurrences = list.groupingBy { it }.eachCount()
        return ocurrences.maxByOrNull { it.value }?.key
    }

    private fun getTopGenre(): String? {
        val list: List<String> = myList.flatMap { it.genres }
        return getMaxOccurences(list)
    }
}