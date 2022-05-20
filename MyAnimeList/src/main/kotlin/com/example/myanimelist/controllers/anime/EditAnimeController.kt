package com.example.myanimelist.controllers.anime

import com.example.myanimelist.extensions.show
import com.example.myanimelist.filters.edition.EditFilters
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.managers.SceneManager
import com.example.myanimelist.views.models.AnimeView
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.TextField

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
    private var editFilters :EditFilters = DependenciesManager.getEditFilter()



    fun saveChanges(actionEvent: ActionEvent) {
        val message = StringBuilder()
        if (!editionFilters(message)) {
            Alert(Alert.AlertType.ERROR).show("Edition invalid", message.toString())
        }else{
            println("correcto")
        }
    }

    private fun editionSave() {
        TODO("Not yet implemented")
    }


    /**
     * Filtrado de datos
     * @param errorMessage mensaje de error qye va a mostrar si hay campos incorrectos
     * @return boolean
     */
    private fun editionFilters(errorMessage: StringBuilder):Boolean {
        if(!editFilters.checkEpisodesCorrect(fieldEpisodes.text)) {
            errorMessage.appendLine("wrong episodes field")
            return false
        }
        if(!editFilters.checkStatusCorrect(fieldStatus.text)) {
            errorMessage.appendLine("wrong status field")
            return false
        }
        if(!editFilters.checkDateCorrect(fieldDate.text)) {
            errorMessage.appendLine("wrong date field")
            return false
        }
        if(!editFilters.checkGenreCorrect(fieldGenre.text)) {
            errorMessage.appendLine("wrong genre field")
            return false
        }

        return true

    }

}