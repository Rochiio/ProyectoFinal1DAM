package com.example.myanimelist.controllers.main

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
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.KeyEvent
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import java.nio.file.Path
import java.sql.SQLException
import java.util.EnumSet
import java.util.logging.LogManager
import java.util.logging.Logger


class MainUserController(
    var user : User,
    var animeStorage: IAnimeStorage,
    var imgStorage: IImgStorage,
    var animeRepository: IAnimeRepository,
    var usersRepository: IUsersRepository,
    val logger : Logger = LogManager.getLogManager().getLogger("main_user.controller")
) {


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

    }

    private fun setEnumCol(consumer: TableColumn<AnimeView, String>, enumSet: ObservableList<*>) {
        consumer.setCellFactory { param -> object: TableCell<AnimeView, String>() {
                override fun updateItem(item: String, empty: Boolean) {
                    val choice: ChoiceBox<*> = ChoiceBox(enumSet)
                    choice.selectionModel.select(enumSet.indexOf(item))
                    graphic = choice
                }
            }
        }
    }

    private fun setTitleCell(consumer : TableColumn<AnimeView, Presentation>) {
        consumer.setCellFactory { param -> object: TableCell<AnimeView, Presentation>(){
                override fun updateItem(item: Presentation, empty: Boolean) {
                    val vbox = VBox()
                    vbox.spacing = 20.0
                    val hbox = HBox()
                    hbox.spacing = 10.0
                    hbox.children.add(Label(item.title))
                    hbox.children.add(Label(item.titleEnglish))
                    val imageview = ImageView()
                    imageview.fitHeight = 40.0
                    imageview.fitWidth = 40.0
                    val dirImage = Path.of(item.img);
                    imageview.image = Image(dirImage.toUri().toString())
                    vbox.children.addAll(imageview, vbox)
                    graphic = vbox
                }
            }
        }
    }

    fun generateHTML(actionEvent: ActionEvent) {

    }

    fun show(actionEvent: ActionEvent) {

    }

    fun sortByText(keyEvent: KeyEvent) {

    }

    private fun loadData() {
        logger.info("loading files")

    }
}