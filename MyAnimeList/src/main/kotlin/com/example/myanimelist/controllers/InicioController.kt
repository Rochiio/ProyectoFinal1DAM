package com.example.myanimelist.controllers

import com.example.myanimelist.MyAnimeListApplication
import com.example.myanimelist.managers.SceneManager
import com.example.myanimelist.utils.viewConfig
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Hyperlink
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.stage.Stage
import java.util.*

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
    @FXML
    lateinit var btnRegister: Hyperlink


    fun changeSceneRegister(actionEvent: ActionEvent) {
        Platform.setImplicitExit(true)
        val stage = btnRegister.scene.window as Stage
        val fxmlLoader = FXMLLoader(MyAnimeListApplication::class.java.getResource("views/registro-view.fxml"))
        val scene = Scene(fxmlLoader.load(), viewConfig.WIDTH.value.toDouble(), viewConfig.HEIGHT.value.toDouble())
        stage.title = "Registro"
        stage.isResizable = false
        stage.scene = scene
        stage.show()
    }
}