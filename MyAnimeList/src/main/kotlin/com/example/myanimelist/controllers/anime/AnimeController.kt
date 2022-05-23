package com.example.myanimelist.controllers.anime

import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.extensions.show
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.repositories.animeList.IRepositoryAnimeList
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.utils.ANIME_DATA_EDIT
import com.example.myanimelist.utils.HEIGHT
import com.example.myanimelist.utils.WIDTH
import com.example.myanimelist.views.models.AnimeView
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.ButtonType
import javafx.scene.control.Label
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
    private val imgStorage = DependenciesManager.getImgStorage()
    private var user = DependenciesManager.globalUser
    private var animeListRepository: IRepositoryAnimeList = DependenciesManager.getAnimeListRepo()
    private var animeRepository: IAnimeRepository = DependenciesManager.getAnimesRepo()
    private var anime: AnimeView = DependenciesManager.animeSelection

    @FXML
    fun initialize() {
        showAnime()
    }


    /**
     *  Usuario Normal
     *  Añadir anime a su lista de animes.
     */
    fun addToList() {
        val stage = txtTittle.scene.window as Stage
        val alert = Alert(Alert.AlertType.CONFIRMATION)
        alert.title = "Confirmación"
        alert.contentText = "Quiere añadir el anime ${txtTittle.text} a su lista"
        val action = alert.showAndWait()

        if (action.get() == ButtonType.OK) {
            val animeAux = animeRepository.findById(anime.id)
            animeListRepository.add(animeAux!!, user)
            user.myList.add(animeAux)
            Alert(Alert.AlertType.INFORMATION).show("Anime Añadido", "${anime.presentation.title} añadido a tu lista")
            logger.info("Añadiendo ${animeAux.title} a la lista del usuario ${user.name}")
            stage.close()
        } else {
            Alert(Alert.AlertType.INFORMATION).show("Saliendo", "Saliendo de Anime-Data")
            stage.close()
        }

    }


    /**
     * Asignar a cada label su información
     */
    private fun showAnime() {
        logger.info("Cargando los datos")
        txtTittle.text = anime.presentation.title
        txtEpisodes.text = anime.episodes.toString()
        txtStatus.text = anime.status
        txtDate.text = anime.date.toString()
        txtGenre.text = anime.genres
        imageAnime.image = imgStorage.loadImg(anime.presentation)
    }


    /**
     * Usuario administrador
     * Editar el anime
     */
    fun editAnime(actionEvent: ActionEvent) {

        val stage = txtTittle.scene.window as Stage
        stage.loadScene(ANIME_DATA_EDIT, WIDTH, HEIGHT) {
            title = "Editor"
            isResizable = false
        }.show()
    }


    /**
     * Usuario administrador
     * Eliminar el anime
     */
    fun deleteAnime(actionEvent: ActionEvent) {
        val alerta = Alert(Alert.AlertType.CONFIRMATION)
        alerta.title = "Confirmación"
        alerta.contentText = "Desea eliminar el anime ${txtTittle.text}"
        val action = alerta.showAndWait()

        if (action.get() == ButtonType.OK) {
            animeRepository.delete(anime.id)
            val result = Alert(Alert.AlertType.CONFIRMATION)
            result.title = "Anime Eliminado"
        }
        val stage = txtTittle.scene.window as Stage
        stage.close()
    }


}




