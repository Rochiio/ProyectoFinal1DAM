package com.example.myanimelist.controllers.anime

import com.example.myanimelist.animeRepository
import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.extensions.show
import com.example.myanimelist.filters.edition.EditFilters
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.utils.HEIGHT
import com.example.myanimelist.utils.MAIN_ADMIN
import com.example.myanimelist.utils.WIDTH
import com.example.myanimelist.views.models.AnimeView
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.stage.Stage
import java.time.LocalDate

class EditAnimeController {
    @FXML
    lateinit var fieldTitle: TextField

    @FXML
    lateinit var fieldEpisodes: TextField

    @FXML
    lateinit var fieldStatus: TextField

    @FXML
    lateinit var fieldDate: TextField

    @FXML
    lateinit var fieldGenre: TextField

    @FXML
    lateinit var btnSave: Button

    private var anime: AnimeView = DependenciesManager.animeSelection
    private var editFilters: EditFilters = DependenciesManager.getEditFilter()


    fun saveChanges() {
        val message = StringBuilder()
        if (!editionFilters(message)) {
            Alert(Alert.AlertType.ERROR).show("Edition invalid", message.toString())
        } else {
            editionSave()
        }
    }


    /**
     * Guardar la acutalizacion del anime
     */
    private fun editionSave() {
        val animeUpdate = creationUpdateAnime()
        animeRepository.update(animeUpdate.toPOJO())
        Alert(Alert.AlertType.INFORMATION).show("Edition correct", "fields changed successfully")
        changeSceneAdmin()
    }

    private fun changeSceneAdmin() {
        val stage = fieldTitle.scene.window as Stage
        //TODO cambiar escena y que te lleve a la vista principal de administrador
        stage.loadScene(MAIN_ADMIN, WIDTH, HEIGHT) {
            title = "Animes"
            isResizable = false
        }.show()
    }


    /**
     * anime parámetros actualizados
     */
    private fun creationUpdateAnime(): AnimeView {
        return AnimeView(
            if (fieldTitle.text.equals(" ")) anime.presentation.title else fieldTitle.text,
            anime.presentation.titleEnglish,
            anime.types,
            if (fieldEpisodes.text.equals(" ")) anime.episodes else fieldEpisodes.text.toInt(),
            if (fieldStatus.text.equals(" ")) anime.status else fieldStatus.text,
            if (fieldDate.text.equals(" ")) anime.date else LocalDate.parse(fieldDate.text),
            anime.rating,
            (if (fieldGenre.text.equals(" ")) anime.genres else listOf(
                fieldGenre.text.split(",").toString()
            )) as List<String>,
            anime.id.toString(),
            anime.id
        )
    }


    /**
     * Filtrado de datos
     * @param errorMessage mensaje de error que va a mostrar si hay campos incorrectos
     * @return boolean
     */
    private fun editionFilters(errorMessage: StringBuilder): Boolean {
        if (!editFilters.checkEpisodesCorrect(fieldEpisodes.text)) {
            errorMessage.appendLine("wrong episodes field")
            return false
        }
        if (!editFilters.checkStatusCorrect(fieldStatus.text)) {
            errorMessage.appendLine("wrong status field")
            return false
        }
        if (!editFilters.checkDateCorrect(fieldDate.text)) {
            errorMessage.appendLine("wrong date field")
            return false
        }
        if (!editFilters.checkGenreCorrect(fieldGenre.text)) {
            errorMessage.appendLine("wrong genre field")
            return false
        }
        return true
    }

}