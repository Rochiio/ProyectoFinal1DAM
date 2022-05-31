package com.example.myanimelist.controllers.main.user

import com.example.myanimelist.extensions.getLogger
import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.managers.CurrentUser
import com.example.myanimelist.managers.ResourcesManager
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.service.img.IImgStorage
import com.example.myanimelist.utils.*
import com.example.myanimelist.utils.Properties
import com.example.myanimelist.views.models.AnimeView
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.collections.transformation.FilteredList
import javafx.fxml.FXML
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.Stage
import org.apache.logging.log4j.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import java.util.*

class MainUserAnimeController : KoinComponent {

    //Generics
    val logger: Logger = getLogger<MainUserAnimeController>()
    val user: CurrentUser by inject()
    val imgStorage: IImgStorage by inject()

    //FXML
    @FXML
    lateinit var addAnimeBtn: Button

    @FXML
    private lateinit var animeListView: ListView<AnimeView>

    @FXML
    private lateinit var searchName: TextField


    //Specific
    private var animeRepository: IAnimeRepository = get()
    private var animeList: ObservableList<AnimeView> = FXCollections.observableArrayList()
    private lateinit var animefl: FilteredList<AnimeView>

    @FXML
    fun initialize() {

        if (!user.isAdmin) addAnimeBtn.isVisible = false

        loadData()

        initCells()

        updateData()

    }

    private fun updateData() {
        animeListView.items = animeList
    }

    private fun loadData() {
        animefl = FilteredList(animeList)
        logger.info("cargando datos a memoria")
        animeList.setAll(animeRepository.findAll().map { AnimeView(it) }.toList())
        animeList.sorted { o1, o2 -> o1.presentation.get().getTitle().compareTo(o2.presentation.get().getTitle()) }
            .forEach { it.ranking.set(animeList.indexOf(it)) }
    }

    private fun initCells() {
        animeListView.setCellFactory {
            object : ListCell<AnimeView>() {
                public override fun updateItem(item: AnimeView?, empty: Boolean) {
                    super.updateItem(item, empty)
                    if (empty) {
                        text = null
                        graphic = null
                        return
                    }

                    //Iniciamos la base
                    val root = HBox()
                    root.spacing = 10.0

                    //Iniciamos los componentes

                    //Ranking
                    val ranking = Label(item!!.ranking.get().toString())

                    //Presentacion (Imagen + titulos)
                    val presentationRoot = HBox()
                    presentationRoot.spacing = 5.0
                    presentationRoot.alignment = Pos.CENTER_LEFT
                    val vBox = VBox()
                    vBox.spacing = 5.0
                    vBox.children.add(Label(item.presentation.get().getTitle()))
                    vBox.children.add(Label(item.presentation.get().getTitleEnglish()))
                    val imageview = ImageView()
                    imageview.fitHeight = 20.0
                    imageview.fitWidth = 20.0
                    imageview.image = imgStorage.loadImg(item.presentation.get())
                    presentationRoot.children.addAll(imageview, vBox)

                    //Rating
                    val rating = Label(item.rating.get())

                    //Boton De Añadir
                    val but = Button()
                    but.alignment = Pos.CENTER
                    but.maxHeight(10.0)
                    but.maxWidth(10.0)
                    val img = ImageView()
                    if (user.isAdmin) img.image = Image(ResourcesManager.getIconOf(Properties.ADD_ICON))
                    else img.image = Image(ResourcesManager.getIconOf(Properties.EDIT_ICON))
                    img.fitHeight = 20.0
                    img.fitWidth = 20.0
                    but.graphic = img
                    but.setOnAction { changeToAnime(item) }

                    //Añadimos todos los campos
                    root.children.addAll(ranking, presentationRoot, rating, but)

                    graphic = root

                }
            }
        }
    }


    fun changeSceneToAnimeDataView(mouseEvent: MouseEvent) {
        if (mouseEvent.button !== MouseButton.PRIMARY || mouseEvent.clickCount != 2) return

        changeToAnime(animeListView.selectionModel.selectedItem)
    }


    /**
     * Cambiar escena al anime seleccionado
     */
    private fun changeToAnime(anime: AnimeView) {
        user.animeSelected = anime

        if (user.isAdmin) {
            Stage().loadScene(ANIME_DATA_ADMIN, WIDTH, HEIGHT) {
                title = anime.presentation.get().getTitle()
                isResizable = false
                icons.add(Image(ResourcesManager.getIconOf("icono.png")))
                setOnCloseRequest { initialize() }
            }.show()
            return
        }

        Stage().loadScene(ANIME_DATA, WIDTH, HEIGHT) {
            title = anime.presentation.get().getTitle()
            isResizable = false
            icons.add(Image(ResourcesManager.getIconOf("icono.png")))
        }.show()
    }


    /**
     * Filtrar lista por texto del buscador
     */
    fun filterByName() {
        if (searchName.text.isEmpty() || searchName.text.isBlank()) return

        animefl.setPredicate {
            it.presentation.get().title.get().uppercase(Locale.getDefault()).contains(
                searchName.text.uppercase(
                    Locale.getDefault()
                )
            )
        }
        animeListView.items = animefl
    }


    /**
     * Cambiar escena a añadir anime
     */
    fun changeViewToAnimeAddView() {
        val stage = addAnimeBtn.scene.window as Stage
        stage.loadScene(ADD_ANIME, WIDTH, HEIGHT) {
            title = "Añadir Anime"
            isResizable = false
            icons.add(Image(ResourcesManager.getIconOf("icono.png")))
        }.show()
    }

}