package com.example.myanimelist.controllers.main

import com.example.myanimelist.controllers.AnimeController
import com.example.myanimelist.javafx.factories.TableItemFactory
import com.example.myanimelist.javafx.factories.AnimeViewTableCell
import com.example.myanimelist.javafx.factories.AnimeViewTableCellPresentation
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.managers.DependenciesManager.getAnimeController
import com.example.myanimelist.managers.DependenciesManager.getAnimesRepo
import com.example.myanimelist.managers.DependenciesManager.getLogger
import com.example.myanimelist.managers.DependenciesManager.getUsersRepo
import com.example.myanimelist.models.enums.Genre
import com.example.myanimelist.models.enums.Status
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.service.anime.IAnimeStorage
import com.example.myanimelist.service.img.IImgStorage
import com.example.myanimelist.views.models.AnimeView
import com.example.myanimelist.views.models.Presentation
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.collections.transformation.FilteredList
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import org.apache.logging.log4j.Logger
import java.util.*


class MainUserController(

) {

    val logger: Logger = getLogger<MainUserController>()
    val user = DependenciesManager.globalUser
    lateinit var animeStorage: IAnimeStorage
    lateinit var imgStorage: IImgStorage
    private var animeRepository: IAnimeRepository = getAnimesRepo()
    private var usersRepository: IUsersRepository = getUsersRepo()
    private var animeController: AnimeController = getAnimeController()
    var itemFactory: TableItemFactory = TableItemFactory()


    private var animeList: ObservableList<AnimeView> = FXCollections.observableArrayList()
    private var myList: ObservableList<AnimeView> = FXCollections.observableArrayList()
    private var flAnime: FilteredList<AnimeView> = FilteredList(FXCollections.observableArrayList())
    private var flMyList: FilteredList<AnimeView> = FilteredList(FXCollections.observableArrayList())

    @FXML
    private lateinit var animeRankingCol: TableColumn<AnimeView, Int>

    @FXML
    private lateinit var animeTitleCol: TableColumn<AnimeView, Presentation>

    @FXML
    private lateinit var animeScoreCol: TableColumn<AnimeView, String>

    @FXML
    private lateinit var myListScoreCol: TableColumn<AnimeView, String>

    @FXML
    private lateinit var myListTypeCol: TableColumn<AnimeView, String>

    @FXML
    private lateinit var myListStatusCol: TableColumn<AnimeView, String>

    @FXML
    private lateinit var myListGenderCol: TableColumn<AnimeView, String>

    @FXML
    private lateinit var myListTitleCol: TableColumn<AnimeView, Presentation>

    @FXML
    private lateinit var myListRankingCol: TableColumn<AnimeView, Int>

    @FXML
    private lateinit var myListTable: TableView<AnimeView>

    @FXML
    private lateinit var animeTable: TableView<AnimeView>

    @FXML
    private lateinit var myListNameSearch: TextField

    @FXML
    private lateinit var animeNameSearch: TextField

    @FXML
    private lateinit var menuButton: Button

    @FXML
    private lateinit var onHoldCount: Label

    @FXML
    private lateinit var finishedCount: Label

    @FXML
    private lateinit var botRankAnime: Label

    @FXML
    private lateinit var topRankAnime: Label

    @FXML
    private lateinit var generateButton: Button

    @FXML
    private lateinit var searchAnimeButon: Button

    @FXML
    private lateinit var searchMyListButon: Button

    @FXML
    fun initialize() {

        loadData()

        logger.info("Iniciando anime columns")
        setAnimeCols()

        logger.info("Iniciando miLista columns")
        setMyListCols()

        logger.info("Iniciando columnas ordenables")
        setFilteredLists()

        setGraphLabels()
    }

    private fun setGraphLabels() {
        val list = usersRepository.getAnimeLists(user.id)
        onHoldCount.text = list.count { it.status == Status.CURRENTLY_AIRING.value }.toString()
        finishedCount.text = list.count { it.status == Status.FINISHED_AIRING.value }.toString()
        botRankAnime.text = list.minByOrNull { it.rating }?.title
        topRankAnime.text = list.maxByOrNull { it.rating }?.title
    }

    private fun setFilteredLists() {
        flAnime = FilteredList(animeList)
        animeTable.items = flAnime

        flMyList = FilteredList(myList)
        myListTable.items = flMyList
    }

    private fun setMyListCols() {
        myListGenderCol.setCellValueFactory { it.value.genresProperty() }
        myListGenderCol.setCellFactory { AnimeViewTableCell(Genre.sample) }

        myListRankingCol.setCellValueFactory { it.value.rankingProperty().asObject() }
        myListScoreCol.setCellValueFactory { it.value.ratingProperty() }
        myListStatusCol.setCellValueFactory { it.value.statusProperty() }
        myListStatusCol.setCellFactory { AnimeViewTableCell(Status.sample) }

        myListTitleCol.setCellValueFactory { it.value.presentationProperty() }
        myListTitleCol.setCellFactory { AnimeViewTableCellPresentation() }

        myListTitleCol.setCellValueFactory { cellData -> cellData.value.presentationProperty() }
        myListTitleCol.setCellFactory {
            object : TableCell<AnimeView, Presentation>() {
                override fun updateItem(item: Presentation, empty: Boolean) {
                    graphic = itemFactory.getAnimePresentation(item)
                }
            }
        }


    }

    private fun setAnimeCols() {
        animeRankingCol.setCellValueFactory { cellData -> cellData.value.rankingProperty().asObject() }
        animeScoreCol.setCellValueFactory { cellData -> cellData.value.ratingProperty() }
        animeTitleCol.setCellValueFactory { cellData -> cellData.value.presentationProperty() }
        animeTitleCol.setCellFactory {
            object : TableCell<AnimeView, Presentation>() {
                override fun updateItem(item: Presentation, empty: Boolean) {
                    graphic = itemFactory.getAnimePresentation(item)
                }
            }
        }
    }

    fun generateHTML(actionEvent: ActionEvent) {
        TODO("generate HTML")
    }

    fun showMenu(actionEvent: ActionEvent) {
        TODO("menu popup")
    }

    private fun loadData() {
        logger.info("cargando datos a memoria")
        animeList.addAll(animeRepository.findAll().map { AnimeView(it) }.toList())
        myList.addAll(usersRepository.getAnimeLists(user.id).map { AnimeView(it) }.toList())

        animeList.sorted { o1, o2 -> o1.presentation.title.compareTo(o2.presentation.title) }
            .forEach { it.ranking = animeList.indexOf(it) }
        myList.sorted { o1, o2 -> o1.presentation.title.compareTo(o2.presentation.title) }
            .forEach { it.ranking = animeList.indexOf(it) }
    }

    fun addToMyList(mouseEvent: MouseEvent) {
        if (mouseEvent.button === MouseButton.PRIMARY && mouseEvent.clickCount == 2) {
            val anime: AnimeView = animeTable.selectionModel.selectedItem
            animeController.initStageAnimeData(anime)
            //addAnime(anime)
        }
    }

    private fun addAnime(animeView: AnimeView) {
        logger.info("Añadido a mi lista \n $animeView")
        val anime = animeView.toPOJO()
        animeRepository.add(anime)
        myListTable.refresh()
        myListTable.selectionModel.select(animeView)
    }

    fun save() {
        TODO("salvar los animes cambiados los usuarios y la lista del usuario")
    }

    fun sortAnime(actionEvent: ActionEvent) {
        logger.info("organizando la lista...")
        flAnime.setPredicate { anime ->
            anime.presentation.title.lowercase(Locale.getDefault()).contains(
                animeNameSearch.text.lowercase(Locale.getDefault()).trim()
            ) ||
                    anime.presentation.titleEnglish.lowercase(Locale.getDefault()).contains(
                        animeNameSearch.text.lowercase(Locale.getDefault()).trim()
                    )
        }
    }

    fun sortMyList(actionEvent: ActionEvent) {
        logger.info("organizando la lista...")
        flAnime.setPredicate { anime ->
            anime.presentation.title.lowercase(Locale.getDefault()).contains(
                myListNameSearch.text.lowercase(Locale.getDefault()).trim()
            ) ||
                    anime.presentation.titleEnglish.lowercase(Locale.getDefault()).contains(
                        myListNameSearch.text.lowercase(Locale.getDefault()).trim()
                    )
        }
    }
}