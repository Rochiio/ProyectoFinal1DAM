package com.example.myanimelist.controllers.main.user

import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.managers.DependenciesManager.getAnimesRepo
import com.example.myanimelist.managers.DependenciesManager.getLogger
import com.example.myanimelist.managers.DependenciesManager.getUsersRepo
import com.example.myanimelist.managers.ResourcesManager
import com.example.myanimelist.managers.SceneManager
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.utils.*
import com.example.myanimelist.views.models.AnimeView
import com.example.myanimelist.views.models.Presentation
import com.example.myanimelist.views.models.ReviewView
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.collections.transformation.FilteredList
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.MenuButton
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.image.Image
import javafx.scene.input.MouseEvent
import javafx.stage.Stage
import org.apache.logging.log4j.Logger


//TODO: Implementar MainUserMyListController

class MainUserMyListController {

    val logger: Logger = getLogger<MainUserMyListController>()
    val user = DependenciesManager.globalUser

    private var animeRepository: IAnimeRepository = getAnimesRepo()
    private var usersRepository: IUsersRepository = getUsersRepo()


    private var animeList: ObservableList<AnimeView> = FXCollections.observableArrayList()
    private var myList: ObservableList<AnimeView> = FXCollections.observableArrayList()
    private var flAnime: FilteredList<AnimeView> = FilteredList(FXCollections.observableArrayList())
    private var flMyList: FilteredList<AnimeView> = FilteredList(FXCollections.observableArrayList())


    @FXML
    private lateinit var myListRankingCol: TableColumn<AnimeView, Int>

    @FXML
    private lateinit var myListTitleCol: TableColumn<AnimeView, Presentation>

    @FXML
    private lateinit var myListScoreCol: TableColumn<ReviewView, Int>

    @FXML
    private lateinit var myListTypeCol: TableColumn<AnimeView, String>

    @FXML
    private lateinit var myListStatusCol: TableColumn<AnimeView, String>

    @FXML
    private lateinit var myListTable: TableView<AnimeView>

    @FXML
    private lateinit var menuButton: MenuButton

    @FXML
    private lateinit var addAnimeButton: Button


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

        //myListTable.items(animeList)
    }

    private fun initCells() {
        myListRankingCol.setCellValueFactory { cellData -> cellData.value.rankingProperty().asObject() }
        myListTitleCol.setCellValueFactory { cellData -> cellData.value.presentationProperty() }
        myListScoreCol.setCellValueFactory { cellData -> cellData.value.scoreProperty().asObject() }
        myListTypeCol.setCellValueFactory { cellData -> cellData.value.typesProperty() }
        myListStatusCol.setCellValueFactory { cellData -> cellData.value.statusProperty() }
    }

    fun openAcercaDe() = SceneManager.openStageAbout()

    fun filterMyListByText(actionEvent: ActionEvent) {

    }

    fun changeSceneToAddAnime(actionEvent: ActionEvent) {
        val stage = addAnimeButton.scene.window as Stage
        stage.loadScene(MAIN_USER_ANIME) {
            title = "Añadir anime"
            isResizable = false
        }.show()

    }

    fun changeMainTheme(actionEvent: ActionEvent) {

    }

    fun logout(actionEvent: ActionEvent) {
        val stage = menuButton.scene.window as Stage
        stage.loadScene(LOGIN) {
            title = "Log in"
            isResizable = false
        }.show()
    }

    fun changeSceneToAnimeView(mouseEvent: MouseEvent) {
        DependenciesManager.animeSelection = myListTable.selectionModel.selectedItem
        if (DependenciesManager.globalUser.admin){
            Stage().loadScene(ANIME_DATA_ADMIN,WIDTH, HEIGHT){
                title="Anime-Data-Admin"
                isResizable= false
                icons.add(Image(ResourcesManager.getIconOf("icono.png")))
            }
        }else{
            Stage().loadScene(ANIME_DATA,WIDTH, HEIGHT){
                title="Anime-Data"
                isResizable= false
                icons.add(Image(ResourcesManager.getIconOf("icono.png")))
            }
        }
    }

    fun changeSceneToProfileUser(actionEvent: ActionEvent) {
        val stage = menuButton.scene.window as Stage
        if (!user.admin) {
            stage.loadScene(PERFIL_VIEW) {
                title = "Perfil usuario"
                isResizable = false
            }.show()
        } else {
            stage.loadScene(PERFIL_VIEW_ADMIN) {
                title = "Perfil usuario admin"
                isResizable = false
            }.show()
        }
    }
}