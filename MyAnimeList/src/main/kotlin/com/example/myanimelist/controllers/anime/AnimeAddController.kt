package com.example.myanimelist.controllers.anime

import com.example.myanimelist.filters.edition.EditFilters
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.models.enums.Genre
import com.example.myanimelist.service.img.IImgStorage
import com.example.myanimelist.utils.Properties
import com.example.myanimelist.views.models.AnimeView
import javafx.fxml.FXML
import javafx.scene.control.ChoiceBox
import javafx.scene.control.DatePicker
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.stage.FileChooser
import org.controlsfx.control.CheckComboBox
import java.io.File

class AnimeAddController {

    //FXML
    @FXML
    private lateinit var txtTitleEnglish: Label
    @FXML
    private lateinit var txtTittle: Label
    @FXML
    private lateinit var txtEpisodes: Label
    @FXML
    private lateinit var datePicker: DatePicker
    @FXML
    private lateinit var statusCB: ChoiceBox<String>
    @FXML
    private lateinit var typeCB: ChoiceBox<String>
    @FXML
    private lateinit var GenreCB: CheckComboBox<String>
    @FXML
    private lateinit var imageAnime: ImageView

    //Specific
    private lateinit var imgFile: File
    private val imgStorage: IImgStorage = DependenciesManager.getImgStorage()
    private lateinit var anime: AnimeView
    private var editFilters: EditFilters = DependenciesManager.getEditFilter()

    @FXML
    fun initialize(){
        GenreCB.items.addAll(Genre.observableValues)
        val genres = anime.genres.split(",")
        val genresSelected = GenreCB.items.filter { genres.any { genre -> it.equals(genre.trim()) } }
        for (genre in genresSelected)
            GenreCB.checkModel.check(genre)
    }

    fun changeAnimeImg() {
        val fc = FileChooser()
        fc.title = "Selecciona una nueva imagen"
        fc.extensionFilters.addAll(
            FileChooser.ExtensionFilter("Imagenes png", "*.png"),
            FileChooser.ExtensionFilter("Imagenes jpg", "*.jpg")
        )
        val file = fc.showOpenDialog(imageAnime.scene.window)

        if (file != null) {
            imageAnime.image = Image(file.toURI().toString())
        }
    }

    fun onSave() {

    }
}