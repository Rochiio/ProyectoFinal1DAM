package com.example.myanimelist.controllers.main

import com.example.myanimelist.factories.TableItemFactory
import com.example.myanimelist.models.User
import com.example.myanimelist.models.enums.Genre
import com.example.myanimelist.models.enums.Status
import com.example.myanimelist.models.enums.Type
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.repositories.users.IUsersRepository
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
import java.sql.SQLException
import java.util.*
import java.util.logging.LogManager
import java.util.logging.Logger


class MainUserController(

) {

    lateinit var user : User
    lateinit var animeStorage: IAnimeStorage
    lateinit var imgStorage: IImgStorage
    lateinit var animeRepository: IAnimeRepository
    lateinit var usersRepository: IUsersRepository
    lateinit var itemFactory: TableItemFactory

    lateinit var animeList: ObservableList<AnimeView>
    lateinit var myList: ObservableList<AnimeView>
    lateinit var flAnime: FilteredList<AnimeView>

    var logger : Logger = LogManager.getLogManager().getLogger("main_user.controller")

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
    private fun initialize() {

        // DaggerRepositoryFactory.create().inject(this);

        try {
            loadData()
        } catch (e: SQLException) {
            logger.warning("error while loading")
        }

        logger.info("Seting anime columns")
        animeRankingCol.setCellValueFactory { cellData -> cellData.value.rankingProperty().asObject() }
        animeScoreCol.setCellValueFactory { cellData -> cellData.value.ratingProperty() }
        animeTitleCol.setCellValueFactory { cellData -> cellData.value.presentationProperty() }
        setTitleCell(animeTitleCol)
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

        flAnime = FilteredList(animeList) { true }
        animeTable.items = flAnime
        animeTable.columns.addAll(animeRankingCol, animeTitleCol, animeScoreCol)
        

    }

    private fun setEnumCol(consumer: TableColumn<AnimeView, String>, enumSet: ObservableList<*>) {
        consumer.setCellFactory {
            object: TableCell<AnimeView, String>() {
                override fun updateItem(item: String, empty: Boolean) {
                    val choiceBox: ChoiceBox<*> = ChoiceBox(enumSet)
                    choiceBox.selectionModel.select(enumSet.indexOf(item))
                    choiceBox.setOnAction {
                        val selection = choiceBox.selectionModel.selectedItem as String
                        val item  = tableView.items[index]
                        item.enumParser(selection)
                    }
                    graphic = choiceBox;
                }
            }
        }
    }
    
    

    private fun setTitleCell(consumer : TableColumn<AnimeView, Presentation>) {
        consumer.setCellFactory { param -> object: TableCell<AnimeView, Presentation>(){
                override fun updateItem(item: Presentation, empty: Boolean) {
                    graphic = itemFactory.getAnimePresentation(item)
                }
            }
        }
    }

    fun generateHTML(actionEvent: ActionEvent) {

    }

    fun showMenu(actionEvent: ActionEvent) {
        TODO("menu popup")
    }

    fun sortAnimeByText(keyEvent: KeyEvent) {
        logger.info("organizando la lista...")
        flAnime.setPredicate { anime ->
            anime.presentation.title.lowercase(Locale.getDefault()).contains(
                animeNameSearch.text.lowercase(Locale.getDefault()).trim())
        }
    }

    private fun loadData() {

        logger.info("loading files")
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
        myListTable.getSelectionModel().select(animeView)
    }

    fun sortMyListByText(keyEvent: KeyEvent) {

    }
}