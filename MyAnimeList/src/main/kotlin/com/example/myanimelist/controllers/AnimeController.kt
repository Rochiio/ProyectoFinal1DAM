package com.example.myanimelist.controllers

import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.repositories.animeList.IRepositoryAnimeList
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.managers.ResourcesManager
import com.example.myanimelist.views.models.AnimeView
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.stage.Stage


class AnimeController {
    @FXML
    lateinit var txtTittle: Label
    @FXML
    lateinit var txtEpisodes: Label
    @FXML
    lateinit var txtStatus: Label
    @FXML
    lateinit var txtDate: Label
    @FXML
    lateinit var txtGenre: Label
    @FXML
    lateinit var imageAnime: ImageView
    @FXML
    lateinit var btnAdd: Button


    private var logger = DependenciesManager.getLogger(AnimeController::class.java)
    lateinit var anime: AnimeView
    private var user = DependenciesManager.globalUser
    private var animeListRepository: IRepositoryAnimeList = DependenciesManager.getAnimeListRepo()
    private var animeRepository: IAnimeRepository = DependenciesManager.getAnimesRepo()

    @FXML
    fun initialize(){
        this.anime = DependenciesManager.animeSelection
        showAnime()
    }

    fun addToList() {
        val alert = Alert(Alert.AlertType.CONFIRMATION)
        alert.title = "Confirmación"
        alert.contentText = "Quiere añadir el anime ${txtTittle.text} a su lista"
        val action = alert.showAndWait()

        if (action.get() == ButtonType.OK) {
            val animeAux= animeRepository.findById(anime.id)
            animeListRepository.add(animeAux!!,user)
            logger.info("Añadiendo ${animeAux.title} a la lista del usuario ${user.name}")

        } else {
            val alerta= Alert(Alert.AlertType.INFORMATION)
            alerta.title="Saliendo del anime"
            val stage = txtTittle.scene.window as Stage
            stage.close()
        }

    }


    private fun showAnime(){
        logger.info("Cargando los datos")
        txtTittle.text=anime.presentation.title
        txtEpisodes.text=anime.episodes.toString()
        txtStatus.text=anime.status
        txtDate.text=anime.date.toString()
        txtGenre.text=anime.genres
        imageAnime.image=Image(ResourcesManager.getCoverOf(anime.presentation.img))
    }
}
