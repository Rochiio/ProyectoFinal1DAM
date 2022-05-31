package com.example.myanimelist.controllers.main.user

import com.example.myanimelist.extensions.getLogger
import com.example.myanimelist.managers.CurrentUser
import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.Review
import com.example.myanimelist.repositories.animeList.IRepositoryAnimeList
import com.example.myanimelist.repositories.reviews.IRepositoryReview
import com.example.myanimelist.utils.html.GeneratorHtml
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.Label
import org.apache.logging.log4j.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class MainUserStatsController : KoinComponent {

    //Generics
    val logger: Logger = getLogger<MainUserAnimeController>()
    val user: CurrentUser by inject()

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

    private val animeListsRepository: IRepositoryAnimeList by inject()
    private val reviewRepository: IRepositoryReview by inject()
    private val htmlGenerator: GeneratorHtml by inject()
    private var myList: List<Anime> = emptyList()
    private var myReviews: List<Review> = emptyList()

    @FXML
    fun initialize() {
        myList = animeListsRepository.findByUserId(user.value)
        //Review del usuario y que este en su lista
        myReviews = reviewRepository.findAll().filter { it.user == user.value && myList.contains(it.anime) }.toList()

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

        estadisticas()

    }


    /**
     * Crear el html de las estadísticas o mostrar un error, dependiendo de los datos
     */
    private fun estadisticas() {
        if (animeCount.text.equals("No hay animes y/o calificaciones suficientes...")) {
            val error = Alert(Alert.AlertType.ERROR)
            error.title = "Error"
            error.headerText = "Error al crear html"
            error.contentText = "No hay datos suficientes para crear las estadísticas"
            error.show()
        } else {
            htmlGenerator.makeHtml(
                animeCount.text,
                topGenreCount.text,
                topTypeCount.text,
                topRatedAnime.text,
                botRatedAnime.text
            )
            val alert = Alert(Alert.AlertType.INFORMATION)
            alert.title = "Estadisticas"
            alert.headerText = "Html de estadisticas creado con existo"
            alert.show()
        }
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