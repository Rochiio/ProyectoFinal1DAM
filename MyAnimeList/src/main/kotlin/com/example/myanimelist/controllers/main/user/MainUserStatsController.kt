package com.example.myanimelist.controllers.main.user

import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.Review
import com.example.myanimelist.repositories.reviews.IRepositoryReview
import com.example.myanimelist.repositories.users.IUsersRepository
import javafx.fxml.FXML
import javafx.scene.control.Label
import org.apache.logging.log4j.Logger
import java.util.stream.Collectors.toMap
import java.util.stream.Stream


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

        if(myList.isEmpty() || myReviews.isEmpty()){
            val emptyMessage = "No hay animes y/o calificaciones suficientes..."
            animeCount.text = emptyMessage
            topGenreCount.text = emptyMessage
            topTypeCount.text = emptyMessage
            topRatedAnime.text = emptyMessage
            botRatedAnime.text = emptyMessage
        }

        animeCount.text = myList.count().toString()
        topGenreCount.text = getTopGenre()
        topTypeCount.text = getMaxOccurences(myList.map { it.types }.toList())
        topRatedAnime.text = myReviews.first { it -> it.score == myReviews.maxOf { it.score } }.anime.title
        botRatedAnime.text = myReviews.first { it -> it.score == myReviews.minOf { it.score } }.anime.title
    }

    private fun getMaxOccurences(list: List<String>): String {
        val map = Stream.of(list)
            .collect(toMap({ w -> w }, { w -> 1 }) { a: Int, b: Int -> Integer.sum(a, b) })

        val max: Int = map.values.stream()
            .mapToInt { n -> n }
            .max().orElse(0)

        return map.filter { e -> max == e.value }.keys.first().first()
    }

    private fun getTopGenre(): String {
        val list: ArrayList<String> = ArrayList()
        for (anime: Anime in myList) {
            anime.genres.toCollection(list)
        }
        return getMaxOccurences(list)
    }
}