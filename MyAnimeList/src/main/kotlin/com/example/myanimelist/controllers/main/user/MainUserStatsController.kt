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

    //Specific
    private var userRepository: IUsersRepository = DependenciesManager.getUsersRepo()
    private var reviewRepository: IRepositoryReview = DependenciesManager.getReviewsRepo()
    private var myList: List<Anime> = emptyList()
    private var myReviews: List<Review> = emptyList()

    @FXML
    fun initialize(){
        myList = userRepository.getAnimeLists(user.id)
        myReviews = reviewRepository.findAll().toList().filter { it.user.id == user.id }

        animeCount.text = myList.count().toString()
        topGenreCount.text = getTopGenre()
        topTypeCount.text = getTopType()
        topRatedAnime.text = myReviews.first{ it -> it.score == myReviews.maxOf { it.score } }.anime.title
        botRatedAnime .text = myReviews.first{ it -> it.score == myReviews.minOf { it.score } }.anime.title
    }

    private fun getTopType(): String? {
        val list = myList.map { it.types }.toList()
        val hm: HashMap<String, Int> = HashMap()
        for (type: String in list){
            if(hm.containsKey(type)){
                hm[type] = hm[type]!!+1
            } else {
                hm[type] = 1
            }
        }
        return hm.filter { it.value == hm.maxOf { it.value } }.keys.first()
    }

    private fun getTopGenre(): String? {
        val list: ArrayList<String> = ArrayList()
        for(anime:Anime in myList){
            anime.types.split(",").toCollection(list)
        }
        val hm: HashMap<String, Int> = HashMap()
        for (genre: String in list){
            if(hm.containsKey(genre)){
                hm[genre] = hm[genre]!!+1
            } else {
                hm[genre] = 1
            }
        }
        return hm.filter { it.value == hm.maxOf { it.value } }.keys.first()
    }
}
