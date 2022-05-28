package com.example.myanimelist.controllers.anime

import com.example.myanimelist.animeRepository
import com.example.myanimelist.extensions.show
import com.example.myanimelist.filters.edition.EditFilters
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.models.enums.Genre
import com.example.myanimelist.models.enums.Status
import com.example.myanimelist.service.img.IImgStorage
import com.example.myanimelist.utils.Properties
import com.example.myanimelist.views.models.AnimeView
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.stage.FileChooser
import javafx.stage.Stage
import javafx.stage.WindowEvent
import org.controlsfx.control.CheckComboBox

class EditAnimeController {

    @FXML
    lateinit var fieldGenres: CheckComboBox<String>

    @FXML
    lateinit var imgViewAnime: ImageView

    @FXML
    lateinit var fieldTitle: TextField

    @FXML
    lateinit var fieldEpisodes: TextField

    @FXML
    lateinit var fieldStatus: ChoiceBox<String>

    @FXML
    lateinit var fieldDate: DatePicker

    @FXML
    lateinit var btnSave: Button

    private val imgStorage: IImgStorage = DependenciesManager.getImgStorage()
    private var anime: AnimeView = DependenciesManager.animeSelection
    private var editFilters: EditFilters = DependenciesManager.getEditFilter()

    @FXML
    fun initialize() {
        fieldTitle.text = anime.presentation.get().getTitle()
        fieldEpisodes.text = anime.episodes.get().toString()
        fieldStatus.items = Status.sample
        fieldStatus.value = anime.status.get()
        fieldDate.value = anime.getDate()
        imgViewAnime.image = imgStorage.loadImg(anime.presentation.get())
        fieldGenres.items.addAll(Genre.observableValues)
        val genres = anime.genres.get().split(",")
        val genresSelected = fieldGenres.items.filter { genres.any { genre -> it.equals(genre.trim()) } }
        for (genre in genresSelected)
            fieldGenres.checkModel.check(genre)
    }

    fun saveChanges() {
        val message = StringBuilder()
        if (!editionFilters(message)) {
            Alert(Alert.AlertType.ERROR).show("Edition invalid", message.toString())
            return
        }

        editionSave()
    }


    /**
     * Guardar la acutalizacion del anime
     */
    private fun editionSave() {
        val animeUpdate = getAnimeView()
        animeRepository.update(animeUpdate.toPOJO())
        Alert(Alert.AlertType.INFORMATION).show("Edition correct", "fields changed successfully")
        val stage = fieldDate.scene.window as Stage
        stage.fireEvent(WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST))
        stage.close()
    }


    /**
     * anime parámetros actualizados
     */
    private fun getAnimeView(): AnimeView {
        return AnimeView(
            if (fieldTitle.text.equals(" ")) anime.presentation.get().getTitle() else fieldTitle.text,
            anime.presentation.get().getTitleEnglish(),
            anime.getTypes(),
            if (fieldEpisodes.text.equals(" ")) anime.episodes.get() else fieldEpisodes.text.toInt(),
            fieldStatus.selectionModel.selectedItem,
            if (fieldDate.value == null) anime.date.get() else fieldDate.value,
            anime.rating.get(),
            fieldGenres.checkModel.checkedItems.joinToString(","),
            "${anime.id}.jpg",
            anime.id
        )
    }


    /**
     * Filtrado de datos
     * @param errorMessage mensaje de error que va a mostrar si hay campos incorrectos
     * @return boolean
     */
    private fun editionFilters(errorMessage: StringBuilder): Boolean {
        if (!editFilters.checkTitleCorrect(fieldTitle.text)) {
            errorMessage.appendLine("wrong title field")
        }
        if (!editFilters.checkEpisodesCorrect(fieldEpisodes.text)) {
            errorMessage.appendLine("wrong episodes field")
        }
        if (!editFilters.checkStatusCorrect(fieldStatus.value)) {
            errorMessage.appendLine("wrong status field")
        }
        if (!editFilters.checkDateCorrect(fieldDate.value)) {
            errorMessage.appendLine("wrong date field")
        }
        if (!(fieldGenres.items.all { editFilters.checkGenreCorrect(it.toString()) })) {
            errorMessage.appendLine("wrong genre field")
        }
        return errorMessage.isEmpty()
    }

    fun changeAnimeImg() {
        val fc = FileChooser()
        fc.title = "Selecciona una nueva imagen"
        fc.extensionFilters.addAll(
            FileChooser.ExtensionFilter("Imagenes png", "*.png"),
            FileChooser.ExtensionFilter("Imagenes jpg", "*.jpg")
        )
        val file = fc.showOpenDialog(imgViewAnime.scene.window)

        if (file != null) {
            imgViewAnime.image = Image(file.toURI().toString())
            anime.presentation.get().setImg(file.name)
            imgStorage.cpFile(file, Properties.COVERS_DIR)
        }
    }

}