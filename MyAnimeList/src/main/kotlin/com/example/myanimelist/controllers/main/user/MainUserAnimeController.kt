package com.example.myanimelist.controllers.main.user

import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.managers.ResourcesManager
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.service.img.IImgStorage
import com.example.myanimelist.utils.*
import com.example.myanimelist.views.models.AnimeView
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.Stage
import org.apache.logging.log4j.Logger

class MainUserAnimeController {


    //Generics
    val logger: Logger = DependenciesManager.getLogger<MainUserAnimeController>()
    val user = DependenciesManager.globalUser
    val imgStorage: IImgStorage = DependenciesManager.getImgStorage()

    //FXML
    @FXML
    private lateinit var animeListView: ListView<AnimeView>


    //Specific
    private var animeList: ObservableList<AnimeView> = FXCollections.observableArrayList()
    private var animeRepository: IAnimeRepository = DependenciesManager.getAnimesRepo()

    @FXML
    fun initialize() {

        loadData()

        initCells()

        initData()

    }

    private fun initData() {
        animeListView.items = animeList
    }

    private fun loadData() {
        logger.info("cargando datos a memoria")
        animeList.addAll(animeRepository.findAll().map { AnimeView(it) }.toList())
        animeList.sorted { o1, o2 -> o1.presentation.title.compareTo(o2.presentation.title) }
            .forEach { it.ranking = animeList.indexOf(it) }
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
                    val ranking = Label(item!!.ranking.toString())

                    //Presentacion (Imagen + titulos)
                    val presentationRoot = HBox()
                    presentationRoot.spacing = 5.0
                    presentationRoot.alignment = Pos.CENTER_LEFT
                    val vBox = VBox()
                    vBox.spacing = 5.0
                    vBox.children.add(Label(item.presentation.title))
                    vBox.children.add(Label(item.presentation.titleEnglish))
                    val imageview = ImageView()
                    imageview.fitHeight = 20.0
                    imageview.fitWidth = 20.0
                    imageview.image = imgStorage.loadImg(item.presentation)
                    presentationRoot.children.addAll(imageview, vBox)

                    //Rating
                    val rating = Label(item.rating)

                    //Boton De Añadir
                    val but = Button()
                    but.alignment = Pos.CENTER
                    but.maxHeight(10.0)
                    but.maxWidth(10.0)
                    val img = ImageView()
                    if (user.admin) img.image = Image(ResourcesManager.getIconOf(Properties.ADD_ICON))
                    else img.image = Image(ResourcesManager.getIconOf(Properties.EDIT_ICON))
                    img.fitHeight = 20.0
                    img.fitWidth = 20.0
                    but.graphic = img
                    but.setOnAction {
                        DependenciesManager.animeSelection = item
                        if (DependenciesManager.globalUser.admin) {
                            Stage().loadScene(ANIME_DATA_ADMIN, WIDTH, HEIGHT) {
                                title = item.presentation.title
                                isResizable = false
                                icons.add(Image(ResourcesManager.getIconOf("editar.png")))
                            }.show()
                        } else {
                            Stage().loadScene(ANIME_DATA, WIDTH, HEIGHT) {
                                title = item.presentation.title
                                isResizable = false
                                icons.add(Image(ResourcesManager.getIconOf("icono.png")))
                            }.show()
                        }
                    }

                    //Añadimos todos los campos
                    root.children.addAll(ranking, presentationRoot, rating, but)

                    graphic = root

                }
            }
        }
    }

    fun changeSceneToAnimeDataView(mouseEvent: MouseEvent) {
        if (mouseEvent.button === MouseButton.PRIMARY && mouseEvent.clickCount == 2) {
            val anime: AnimeView = animeListView.selectionModel.selectedItem
            DependenciesManager.animeSelection = anime
            if (DependenciesManager.globalUser.admin) {
                Stage().loadScene(ANIME_DATA_ADMIN, WIDTH, HEIGHT) {
                    title = anime.presentation.title
                    isResizable = false
                    icons.add(Image(ResourcesManager.getIconOf("icono.png")))
                }.show()
            } else {
                Stage().loadScene(ANIME_DATA, WIDTH, HEIGHT) {
                    title = anime.presentation.title
                    isResizable = false
                    icons.add(Image(ResourcesManager.getIconOf("icono.png")))
                }.show()
            }
        }
    }
}