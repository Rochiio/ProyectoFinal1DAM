package com.example.myanimelist.controllers.main

import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.User
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.input.KeyEvent
import java.sql.SQLException
import java.util.logging.LogManager
import java.util.logging.Logger


class MainUserController {


    val logger : Logger = LogManager.getLogManager().getLogger("mainuser.controller")
    lateinit var user : User;

    @FXML
    private lateinit var myListTable: TableView<Anime>
    @FXML
    private lateinit var animeTable: TableView<Anime>
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

    fun generateHTML(actionEvent: ActionEvent) {

    }

    fun show(actionEvent: ActionEvent) {

    }

    fun sortByText(keyEvent: KeyEvent) {

    }

    private fun loadData() {
        logger.info("Cargando datos...")

    }
}