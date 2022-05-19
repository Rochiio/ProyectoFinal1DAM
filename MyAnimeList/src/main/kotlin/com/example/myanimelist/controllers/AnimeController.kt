package com.example.myanimelist.controllers

import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.managers.SceneManager
import com.example.myanimelist.models.enums.Genre
import com.example.myanimelist.models.enums.Status
import com.example.myanimelist.models.enums.Type
import com.example.myanimelist.repositories.animeList.IRepositoryAnimeList
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.utils.ANIME_DATA
import com.example.myanimelist.utils.HEIGHT
import com.example.myanimelist.utils.LOGIN
import com.example.myanimelist.utils.WIDTH
import com.example.myanimelist.views.models.AnimeView
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.ButtonType
import javafx.scene.control.Label
import javafx.stage.Stage
import javafx.stage.StageStyle
import java.time.LocalDate
import java.util.*


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
     lateinit var btnAdd: Button

    private var logger = DependenciesManager.getLogger(AnimeController::class.java)
    private lateinit var anime: AnimeView
    private var animeListRepository: IRepositoryAnimeList = DependenciesManager.getAnimeListRepo()
    private var animeRepository: IAnimeRepository = DependenciesManager.getAnimesRepo()



    fun addToList(actionEvent: ActionEvent) {
        val alert = Alert(Alert.AlertType.CONFIRMATION)
        alert.title = "Confirmación"
        alert.contentText = "Quiere añadir el anime ${txtTittle.text} a su lista"
        val action = alert.showAndWait()

            if (action.get() == ButtonType.OK) {
                val user = DependenciesManager.globalUser
                val animeAux= animeRepository.findById(anime.id)
                animeListRepository.add(animeAux!!,user)
                logger.info("Añadiendo ${animeAux.title} a la lista del usuario ${user.name}")

            } else {
                val alert= Alert(Alert.AlertType.INFORMATION)
                alert.title="Saliendo del anime"
                val stage = txtTittle.scene.window as Stage
                stage.close()
            }

    }

    fun initStageAnimeData(anime: AnimeView){
        this.anime= anime
        val stage = Stage().loadScene(ANIME_DATA, WIDTH, HEIGHT) {
            isResizable = false
            title = "Data"
        }

        showAnime()
        stage.show()
    }

    private fun showAnime(){
        txtTittle.text=anime.presentation.title
        txtEpisodes.text=anime.episodes.toString()
        txtStatus.text=anime.status.toString()
        txtDate.text=anime.date.toString()
        txtGenre.text=anime.genres.toString()

    }
}
