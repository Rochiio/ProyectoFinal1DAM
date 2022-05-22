package com.example.myanimelist.controllers.main.user

import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.service.img.IImgStorage
import com.example.myanimelist.utils.*
import com.example.myanimelist.views.models.AnimeView
import javafx.collections.FXCollections
import javafx.collections.ObservableList
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

    }

    private fun loadData() {
        logger.info("cargando datos a memoria")
        animeList.addAll(animeRepository.findAll().map { AnimeView(it) }.toList())
        animeList.sorted { o1, o2 -> o1.presentation.title.compareTo(o2.presentation.title) }
            .forEach { it.ranking = animeList.indexOf(it) }
    }

    private fun initCells() {
        animeListView.setCellFactory {object:ListCell<AnimeView>(){
            override fun updateItem(item: AnimeView?, empty: Boolean) {
                super.updateItem(item, empty)
                if (empty) {
                    text = null
                    graphic = null
                    return
                }

                //Iniciamos la base
                val root = HBox()
                root.spacing = 30.0

                //Iniciamos los componentes

                //Ranking
                val ranking = Label(item!!.ranking.toString())

                //Presentacion (Imagen + titulos)
                val presentationRoot = VBox()
                presentationRoot.spacing = 20.0
                presentationRoot.alignment = Pos.CENTER_LEFT
                val hbox = HBox()
                hbox.spacing = 10.0
                hbox.children.add(Label(item.presentation.title))
                hbox.children.add(Label(item.presentation.titleEnglish))
                val imageview = ImageView()
                imageview.fitHeight = 40.0
                imageview.fitWidth = 40.0
                imageview.image = imgStorage.loadImg(item.presentation)
                presentationRoot.children.add(imageview)
                presentationRoot.children.add(hbox)

                //Rating
                val rating = Label(item.rating)

                //Boton De Añadir
                val but = Button()
                but.alignment = Pos.CENTER
                val img = ImageView()
                img.image = Image(Properties.ADD_ICON)
                img.fitHeight = but.height
                img.fitWidth = but.width
                but.graphic = img
                but.setOnAction {
                    DependenciesManager.animeSelection = item
                    Stage().loadScene(ANIME_DATA, WIDTH, HEIGHT) {
                        title = item.presentation.title
                        isResizable = false
                    }.show()
                }
                //Hacemos el setGraphic

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
            Stage().loadScene(ANIME_DATA, WIDTH, HEIGHT){
                title = anime.presentation.title
                isResizable = false
            }.show()
        }
    }
}