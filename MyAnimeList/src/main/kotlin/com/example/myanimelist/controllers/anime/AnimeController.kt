package com.example.myanimelist.controllers.anime

import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.extensions.show
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.models.Review
import com.example.myanimelist.repositories.animeList.IRepositoryAnimeList
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.repositories.reviews.IRepositoryReview
import com.example.myanimelist.utils.ANIME_DATA_EDIT
import com.example.myanimelist.utils.HEIGHT
import com.example.myanimelist.utils.WIDTH
import com.example.myanimelist.views.models.AnimeView
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.image.ImageView
import javafx.stage.Stage
import javafx.stage.WindowEvent
import org.controlsfx.control.Rating


class AnimeController {
    @FXML
    lateinit var saveReviewButton: Button

    @FXML
    lateinit var reviewField: TextArea

    @FXML
    lateinit var rating: Rating

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
    private var reviewRepository: IRepositoryReview = DependenciesManager.getReviewsRepo()
    private var anime: AnimeView = DependenciesManager.animeSelection

    @FXML
    fun initialize() {
        showAnime()
        if (!user.admin)
            loadReview()
    }

    private fun loadReview() {
        val review = reviewRepository.findByAnimeId(anime.id).firstOrNull { it.user.id == user.id } ?: return
        reviewField.text = review.comment
        rating.rating = review.score.toDouble()
        disableReviewFields()
    }

    @FXML
    fun saveReview() {
        val wantsToSave = {
            Alert(Alert.AlertType.CONFIRMATION).show(
                "Guardar review",
                "Una vez se guarde no se podra modificar"
            ) == ButtonType.OK
        }
        val error = StringBuilder()

        if (!validateReviewFields(error)) {
            Alert(Alert.AlertType.WARNING).show("Campos invalidos", error.toString())
            return
        }

        if (!wantsToSave()) return

        val review = Review(anime.toPOJO(), user, rating.rating.toInt(), reviewField.text)
        reviewRepository.add(review)
        disableReviewFields()
    }

    private fun disableReviewFields() {
        reviewField.isDisable = true
        rating.isDisable = true
        saveReviewButton.isDisable = true
    }

    private fun validateReviewFields(error: StringBuilder): Boolean {
        if (reviewField.text.isNullOrBlank())
            error.appendLine("El comentario no puede estar vacio")
        if (rating.rating == 0.0)
            error.appendLine("El numero de estrellas no puede estar vacio")
        return error.isEmpty()
    }


    /**
     *  Usuario Normal
     *  Añadir anime a su lista de animes.
     */
    @FXML
    fun addToList() {
        val stage = txtTittle.scene.window as Stage
        val animeAux = anime.toPOJO()

        val wantsToAdd: () -> Boolean = {
            Alert(Alert.AlertType.CONFIRMATION).show(
                "Confirmación",
                "Quiere añadir el anime ${txtTittle.text} a su lista"
            ) == ButtonType.OK
        }

        if (animeListRepository.findByUserId(user).contains(animeAux)) {
            Alert(Alert.AlertType.INFORMATION).show(
                "Anime ya añadido",
                "${animeAux.title} ya esta en tu lista"
            )
            return
        }

        if (!wantsToAdd()) {
            Alert(Alert.AlertType.INFORMATION).show("Saliendo", "Saliendo de Anime-Data")
            stage.close()
            return
        }


        animeListRepository.add(animeAux, user)

        Alert(Alert.AlertType.INFORMATION).show(
            "Anime Añadido",
            "${anime.presentation.get().getTitle()} añadido a tu lista"
        )
        logger.info("Añadiendo ${animeAux.title} a la lista del usuario ${user.name}")

        stage.close()

    }


    /**
     * Asignar a cada label su información
     */
    private fun showAnime() {
        logger.info("Cargando los datos")
        txtTittle.text = anime.presentation.get().getTitle()
        txtEpisodes.text = anime.episodes.get().toString()
        txtStatus.text = anime.status.get()
        txtDate.text = anime.date.get().toString()
        txtGenre.text = anime.genres.get()
        imageAnime.image = imgStorage.loadImg(anime.presentation.get())
    }


    /**
     * Usuario administrador
     * Editar el anime
     */
    fun editAnime() {
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
    fun deleteAnime() {
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
        stage.fireEvent(WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST))
        stage.close()
    }


}




