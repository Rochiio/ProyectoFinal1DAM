package com.example.myanimelist.controllers.main.user

import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.Review
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.repositories.reviews.IRepositoryReview
import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.views.models.AnimeView
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.collections.transformation.FilteredList
import javafx.fxml.FXML
import javafx.scene.control.Label
import org.apache.logging.log4j.Logger
import java.util.stream.Collectors


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

    //Specific
    private var userRepository: IUsersRepository = DependenciesManager.getUsersRepo()
    private var reviewRepository: IRepositoryReview = DependenciesManager.getReviewsRepo()
    lateinit private var myList: List<Anime>
    lateinit private var myReviews: List<Review>

    @FXML
    fun initialize(){
        myList = userRepository.getAnimeLists(user.id)
        myReviews = reviewRepository.findAll().toList().filter { it.user.id == user.id }

        animeCount.text = myList.count().toString()
        val list: ArrayList<String>
        topGenreCount.text = getTopGenre()
    }

    private fun getTopGenre(): String? {
        var list: ArrayList<String> = ArrayList()
        for(anime:Anime in myList){
            anime.types.split(",").toCollection(list)
        }
        list.sort()
        list.groupBy { it }
        TODO("seguir con la logica que coñazo macho")
        return ""
    }
}