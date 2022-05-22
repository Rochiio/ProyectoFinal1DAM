package com.example.myanimelist.controllers.main.user

import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.utils.*
import com.example.myanimelist.views.models.AnimeView
import com.example.myanimelist.views.models.Presentation
import com.example.myanimelist.views.tableextensions.PresentationCellFactory
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.collections.transformation.FilteredList
import javafx.fxml.FXML
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.TableCell
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.stage.Stage
import org.apache.logging.log4j.Logger

class MainUserAnimeController {

    //Generics
    val logger: Logger = DependenciesManager.getLogger<MainUserAnimeController>()
    val user = DependenciesManager.globalUser

    //FXML
    @FXML
    private lateinit var animeButtonCol: TableColumn<AnimeView, Button>

    @FXML
    private lateinit var animeRatingCol: TableColumn<AnimeView, String>

    @FXML
    private lateinit var animeTitleCol: TableColumn<AnimeView, Presentation>

    @FXML
    private lateinit var animeRankingCol: TableColumn<AnimeView, Int>

    @FXML
    private var animeTable: TableView<AnimeView> = TableView()

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
        animeRankingCol.setCellValueFactory { cellData -> cellData.value.rankingProperty().asObject() }
        animeTitleCol.setCellValueFactory { cellData -> cellData.value.presentationProperty() }
        animeTitleCol.setCellFactory { PresentationCellFactory() }
        animeRatingCol.setCellValueFactory { cellData -> cellData.value.ratingProperty() }
        animeButtonCol.setCellFactory {
            object : TableCell<AnimeView, Button>() {
                override fun updateItem(item: Button?, empty: Boolean) {
                    val but = Button()
                    but.alignment = Pos.CENTER
                    val img = ImageView()
                    img.image = Image(Properties.ADD_ICON)
                    img.fitHeight = but.height
                    img.fitWidth = but.width
                    but.graphic = img
                    but.setOnAction {
                        val anime: AnimeView = animeTable.selectionModel.selectedItem
                        DependenciesManager.animeSelection = anime
                        Stage().loadScene(ANIME_DATA, WIDTH, HEIGHT) {
                            title = anime.presentation.title
                            isResizable = false
                        }.show()
                    }
                    graphic = but
                }
            }
        }
        animeTable.items = animeList
        animeTable.columns.addAll(animeRankingCol, animeTitleCol, animeRatingCol, animeButtonCol)
    }

    fun changeSceneToAnimeDataView(mouseEvent: MouseEvent) {
        if (mouseEvent.button === MouseButton.PRIMARY && mouseEvent.clickCount == 2) {
            val anime: AnimeView = animeTable.selectionModel.selectedItem
            DependenciesManager.animeSelection = anime
            Stage().loadScene(ANIME_DATA, WIDTH, HEIGHT) {
                title = anime.presentation.title
                isResizable = false
            }.show()
        }
    }
}