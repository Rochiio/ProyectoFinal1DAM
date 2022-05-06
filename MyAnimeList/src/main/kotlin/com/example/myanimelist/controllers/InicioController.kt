package com.example.myanimelist.controllers

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField

class InicioController {
    @FXML
    lateinit var txtUsername: TextField
    @FXML
    lateinit var txtPassword: PasswordField
    @FXML
    lateinit var txtUsernameRegister: TextField
    @FXML
    lateinit var txtEmail: TextField
    @FXML
    lateinit var txtPasswordRegister: PasswordField
    @FXML
    lateinit var txtConfirmPassword: PasswordField


    fun changeSceneRegister(actionEvent: ActionEvent) {

    }
}