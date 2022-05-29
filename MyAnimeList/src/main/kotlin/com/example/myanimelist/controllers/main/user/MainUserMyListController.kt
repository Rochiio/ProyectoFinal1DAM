package com.example.myanimelist.controllers.main.user

import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.managers.DependenciesManager.getLogger
import com.example.myanimelist.managers.ResourcesManager
import com.example.myanimelist.managers.SceneManager
import com.example.myanimelist.repositories.animeList.IRepositoryAnimeList
import com.example.myanimelist.service.txt.TxtBackup
import com.example.myanimelist.utils.*
import com.example.myanimelist.views.models.AnimeView
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.stage.Stage
import org.apache.logging.log4j.Logger


class MainUserMyListController {

    val logger: Logger = getLogger<MainUserMyListController>()
    val user = DependenciesManager.globalUser

    private var animeListRepository: IRepositoryAnimeList = DependenciesManager.getAnimeListRepo()
    private var animeList: ObservableList<AnimeView> = FXCollections.observableArrayList()

    @FXML
    private lateinit var myListRankingCol: TableColumn<AnimeView, Int>

    @FXML
    private lateinit var myListTitleCol: TableColumn<AnimeView, String>

    /*@FXML
    private lateinit var myListScoreCol: TableColumn<ReviewView, Int>*/

    @FXML
    private lateinit var myListTypeCol: TableColumn<AnimeView, String>

    @FXML
    private lateinit var myListStatusCol: TableColumn<AnimeView, String>

    @FXML
    private lateinit var myListTable: TableView<AnimeView>

    @FXML
    private lateinit var menuButton: MenuButton


    @FXML
    fun initialize() {
        //TODO("que se establezca el tema a ThemesManager.currentTheme")
        loadData()
        initCells()
    }

    private fun loadData() {
        logger.info("cargando datos a memoria")
        animeList.setAll(user.myList.map { AnimeView(it) }.toList())

    }

    private fun initCells() {
        myListTable.items = animeList

        myListRankingCol.setCellValueFactory { it.value.rankingProperty().asObject() }
        myListTitleCol.setCellValueFactory { it.value.presentationProperty().get().title }
        // myListScoreCol.setCellValueFactory { cellData -> cellData.value.scoreProperty().asObject() }
        myListTypeCol.setCellValueFactory { it.value.typesProperty() }
        myListStatusCol.setCellValueFactory { it.value.statusProperty() }
    }

    fun openAcercaDe() = SceneManager.openStageAbout()

    fun filterMyListByText() {

    }

    fun changeSceneToAddAnime() {
        val stage = Stage()
        stage.loadScene(MAIN_USER_ANIME) {
            title = "Añadir anime"
            isResizable = false
            icons.add(Image(ResourcesManager.getIconOf("icono.png")))
        }.show()

    }

    fun changeMainTheme() {
        ThemesManager.changeTheme()
        ThemesManager.setTheme(menuButton)
        TxtBackup.changeNightMode(ThemesManager.currentTheme)
    }

    fun logout() {
        val stage = menuButton.scene.window as Stage
        stage.loadScene(LOGIN) {
            title = "Log in"
            isResizable = false
            icons.add(Image(ResourcesManager.getIconOf("icono.png")))
        }.show()
    }

    fun changeSceneToAnimeView(mouseEvent: MouseEvent) {
        if (mouseEvent.button == MouseButton.PRIMARY && mouseEvent.clickCount == 2) {
            DependenciesManager.animeSelection = myListTable.selectionModel.selectedItem
            if (DependenciesManager.globalUser.admin) {
                Stage().loadScene(ANIME_DATA_ADMIN, WIDTH, HEIGHT) {
                    title = "Anime-Data-Admin"
                    isResizable = false
                    icons.add(Image(ResourcesManager.getIconOf("icono.png")))
                }.show()
            } else {
                Stage().loadScene(ANIME_DATA, WIDTH, HEIGHT) {
                    title = "Anime-Data"
                    isResizable = false
                    icons.add(Image(ResourcesManager.getIconOf("icono.png")))
                }.show()
            }
        }
    }

    fun changeSceneToProfileUser() {

        if (!user.admin) {
            Stage().loadScene(PERFIL_VIEW) {
                title = "Perfil usuario"
                isResizable = false
            }.show()
        } else {
            Stage().loadScene(PERFIL_VIEW_ADMIN) {
                title = "Perfil usuario admin"
                isResizable = false
            }.show()
        }
    }

    fun changeSceneToStatsUser() {
        Stage().loadScene(MAIN_USER_STATS) {
            title = "Estadisticas"
            isResizable = false
        }.show()
    }

    fun refreshTable() {
        loadData()
        myListTable.refresh()
    }

    fun deleteAnimeMyList() {
        val animeSelect =myListTable.selectionModel.selectedItem
        val alert = Alert(Alert.AlertType.CONFIRMATION)
        alert.title="Confirmación"
        alert.headerText="Desea eliminar el anime ${animeSelect.presentation.get().getTitle()} de su lista"
        val result = alert.showAndWait()

        if (result.get()== ButtonType.OK){
            animeListRepository.delete(animeSelect.toPOJO(),user)
            animeList.remove(animeSelect)

            val information =Alert(Alert.AlertType.INFORMATION)
            information.title="Anime eliminado"
            information.headerText="Anime eliminado de su lista correctamente"
            information.show()
        }

    }
}