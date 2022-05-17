package com.example.myanimelist.controllers.main

import com.example.myanimelist.factories.TableItemFactory
import com.example.myanimelist.manager.DataBaseManager
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.managers.DependenciesManager.getAnimesRepo
import com.example.myanimelist.managers.DependenciesManager.getLogger
import com.example.myanimelist.managers.DependenciesManager.getUsersRepo
import com.example.myanimelist.models.User
import com.example.myanimelist.models.enums.Genre
import com.example.myanimelist.models.enums.Status
import com.example.myanimelist.models.enums.Type
import com.example.myanimelist.repositories.animes.AnimeRepository
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.repositories.users.UsersRepository
import com.example.myanimelist.service.anime.IAnimeStorage
import com.example.myanimelist.service.img.IImgStorage
import com.example.myanimelist.views.models.AnimeView
import com.example.myanimelist.views.models.Presentation
import javafx.collections.ObservableList
import javafx.collections.transformation.FilteredList
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.sql.SQLException
import java.util.*



class MainUserController(

) {

    val logger : Logger = getLogger<MainUserController>()
    val user = DependenciesManager.globalUser
    lateinit var animeStorage: IAnimeStorage
    lateinit var imgStorage: IImgStorage
    var animeRepository: IAnimeRepository = getAnimesRepo()
    var usersRepository: IUsersRepository = getUsersRepo()
    var itemFactory: TableItemFactory = TableItemFactory()

    lateinit var animeList: ObservableList<AnimeView>
    lateinit var myList: ObservableList<AnimeView>
    private lateinit var flAnime: FilteredList<AnimeView>
    private lateinit var flMyList: FilteredList<AnimeView>

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

    init {

        // DaggerRepositoryFactory.create().inject(this);

        try {
            loadData()
        } catch (e: SQLException) {
            logger.warn("error while loading")
        }

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
        flAnime = FilteredList(animeList) { true }
        animeTable.items = flAnime

        flMyList = FilteredList(myList) { true }
        myListTable.items = flMyList
    }

    private fun setMyListCols() {
        myListGenderCol.setCellValueFactory { cellData -> cellData.value.genresProperty() }
        setEnumCol(myListGenderCol, Genre.sample)

        myListRankingCol.setCellValueFactory { cellData -> cellData.value.rankingProperty().asObject() }
        myListScoreCol.setCellValueFactory { cellData -> cellData.value.ratingProperty() }
        myListStatusCol.setCellValueFactory { cellData -> cellData.value.statusProperty() }
        setEnumCol(myListStatusCol, Status.sample)

        myListTitleCol.setCellValueFactory { cellData -> cellData.value.presentationProperty() }
        setTitleCell(myListTitleCol)

        myListTypeCol.setCellValueFactory { cellData -> cellData.value.typesProperty() }
        setEnumCol(myListTypeCol, Type.sample)
    }

    private fun setAnimeCols() {
        animeRankingCol.setCellValueFactory { cellData -> cellData.value.rankingProperty().asObject() }
        animeScoreCol.setCellValueFactory { cellData -> cellData.value.ratingProperty() }
        animeTitleCol.setCellValueFactory { cellData -> cellData.value.presentationProperty() }
        setTitleCell(animeTitleCol)
    }

    private fun setEnumCol(consumer: TableColumn<AnimeView, String>, enumSet: ObservableList<*>) {
        consumer.setCellFactory {
            object: TableCell<AnimeView, String>() {
                override fun updateItem(item: String, empty: Boolean) {
                    val choiceBox: ChoiceBox<*> = ChoiceBox(enumSet)
                    choiceBox.selectionModel.select(enumSet.indexOf(item))
                    choiceBox.setOnAction {
                        val selection = choiceBox.selectionModel.selectedItem as String
                        val anime  = tableView.items[index]
                        anime.enumParser(selection)
                    }
                    graphic = choiceBox;
                }
            }
        }
    }
    
    

    private fun setTitleCell(consumer : TableColumn<AnimeView, Presentation>) {
        consumer.setCellFactory {
            object: TableCell<AnimeView, Presentation>(){
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

    fun sortAnimeByText(keyEvent: KeyEvent) {
        logger.info("organizando la lista...")
        flAnime.setPredicate { anime ->
            anime.presentation.title.lowercase(Locale.getDefault()).contains(
                animeNameSearch.text.lowercase(Locale.getDefault()).trim()) ||
            anime.presentation.titleEnglish.lowercase(Locale.getDefault()).contains(
                animeNameSearch.text.lowercase(Locale.getDefault()).trim())
        }
    }

    private fun loadData() {

        logger.info("cargando datos a memoria")
        animeList.addAll(animeRepository.findAll().map { AnimeView(it) }.toList())
        myList.addAll(usersRepository.getAnimeLists(user.id).map { AnimeView(it) }.toList())
    }

    fun addToMyList(mouseEvent: MouseEvent) {
        if (mouseEvent.button === MouseButton.PRIMARY && mouseEvent.clickCount == 2) {
            val anime: AnimeView = animeTable.selectionModel.selectedItem
            addAnime(anime)
        }
    }

    private fun addAnime(animeView: AnimeView) {
        logger.info("Añadido a mi lista \n $animeView")
        val anime = animeView.toPOJO()
        animeRepository.add(anime)
        myListTable.refresh()
        myListTable.selectionModel.select(animeView)
    }

    fun sortMyListByText(keyEvent: KeyEvent) {
        logger.info("organizando la lista...")
        flAnime.setPredicate { anime ->
            anime.presentation.title.lowercase(Locale.getDefault()).contains(
                myListNameSearch.text.lowercase(Locale.getDefault()).trim()) ||
            anime.presentation.titleEnglish.lowercase(Locale.getDefault()).contains(
                myListNameSearch.text.lowercase(Locale.getDefault()).trim())
        }
    }

    fun save(){
        TODO("salvar los animes cambiados los usuarios y la lista del usuario")
    }
}