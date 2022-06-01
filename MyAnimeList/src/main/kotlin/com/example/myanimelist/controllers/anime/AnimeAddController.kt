package com.example.myanimelist.controllers.anime

import com.example.myanimelist.extensions.show
import com.example.myanimelist.filters.edition.EditFilters
import com.example.myanimelist.models.enums.Genre
import com.example.myanimelist.models.enums.Status
import com.example.myanimelist.models.enums.Type
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.service.img.IImgStorage
import com.example.myanimelist.utils.Properties
import com.example.myanimelist.views.models.AnimeView
import javafx.beans.property.SimpleStringProperty
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.ChoiceBox
import javafx.scene.control.DatePicker
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.stage.FileChooser
import javafx.stage.Stage
import javafx.stage.WindowEvent
import org.controlsfx.control.CheckComboBox
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.File
import java.util.*

class AnimeAddController : KoinComponent {

    //FXML
    @FXML
    lateinit var txtTitleEnglish: TextField

    @FXML
    lateinit var txtTitle: TextField

    @FXML
    lateinit var txtEpisodes: TextField

    @FXML
    lateinit var datePicker: DatePicker

    @FXML
    lateinit var statusCB: ChoiceBox<String>

    @FXML
    lateinit var typeCB: ChoiceBox<String>

    @FXML
    lateinit var genreCB: CheckComboBox<String>

    @FXML
    lateinit var imageAnime: ImageView

    //Specific
    private val animeRepository: IAnimeRepository by inject()
    private val imgStorage: IImgStorage by inject()
    private val editFilters: EditFilters by inject()
    private var imgFile: File? = null

    @FXML
    fun initialize() {
        txtTitleEnglish
        txtTitle
        txtEpisodes
        genreCB.items.addAll(Genre.sample)
        typeCB.items.addAll(Type.sample)
        statusCB.items.addAll(Status.sample)
    }


    /**
     *
     */
    fun changeAnimeImg() {
        val fc = FileChooser()
        fc.title = "Selecciona una nueva imagen"
        fc.extensionFilters.addAll(
            FileChooser.ExtensionFilter("Imagenes png", "*.png"),
            FileChooser.ExtensionFilter("Imagenes jpg", "*.jpg")
        )
        imgFile = fc.showOpenDialog(imageAnime.scene.window)
        if (imgFile != null) imageAnime.image = Image(imgFile?.toURI().toString())
    }


    fun onSave() {
        val message = StringBuilder()
        if (!editionFilters(message)) {
            Alert(Alert.AlertType.ERROR).show("Edition invalid", message.toString())
            return
        }
        saveThis()
    }


    /**
     * Para guardar el anime que acabamos de crear
     */
    private fun saveThis() {
        val anime = getAnimeView()
        animeRepository.add(anime.toPOJO())
        Alert(Alert.AlertType.INFORMATION).show("Anime añadido", "anime añadido correctamente")
        val stage = datePicker.scene.window as Stage
        stage.fireEvent(WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST))
        stage.close()
    }


    /**
     * Creación del nuevo anime
     * @return anime creado
     */
    private fun getAnimeView(): AnimeView {
        val anime = AnimeView(
            txtTitle.text,
            txtTitleEnglish.text,
            typeCB.selectionModel.selectedItem,
            txtEpisodes.text.toInt(),
            statusCB.selectionModel.selectedItem,
            datePicker.value,
            "PG13",
            genreCB.checkModel.checkedItems.joinToString(","),
            UUID.randomUUID()
        )
        if (imgFile != null) {
            imgStorage.cpFile(imgFile!!, Properties.COVERS_DIR)
            anime.presentation.get().img = SimpleStringProperty(imgFile!!.name)
        }

        return anime
    }


    /**
     * Filtros para validar el nuevo anime que queremos añadir
     * @param errorMessage Mensaje de error si el anime no pasa los filtros
     * @return boolean dependiendo de si pasa los filtros correctamente o no
     */
    private fun editionFilters(errorMessage: StringBuilder): Boolean {
        if (!editFilters.checkTitleCorrect(txtTitle.text)) {
            errorMessage.appendLine("wrong title field")
        }
        if (!editFilters.checkEpisodesCorrect(txtEpisodes.text)) {
            errorMessage.appendLine("wrong episodes field")
        }
        if (!editFilters.checkStatusCorrect(statusCB.value)) {
            errorMessage.appendLine("wrong status field")
        }
        if (!editFilters.checkDateCorrect(datePicker.value)) {
            errorMessage.appendLine("wrong date field")
        }
        if (!(genreCB.items.all { editFilters.checkGenreCorrect(it.toString()) })) {
            errorMessage.appendLine("wrong genre field")
        }
        return errorMessage.isEmpty()
    }
}