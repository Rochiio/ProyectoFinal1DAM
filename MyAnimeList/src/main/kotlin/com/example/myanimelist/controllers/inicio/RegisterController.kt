package com.example.myanimelist.controllers.inicio

import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.models.User
import com.example.myanimelist.utils.HEIGHT
import com.example.myanimelist.utils.LOGIN
import com.example.myanimelist.utils.WIDTH
import javafx.fxml.FXML
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.stage.Stage
import java.sql.Date
import java.util.*

class RegisterController : InicioController() {
    @FXML
    private lateinit var txtEmail: TextField

    @FXML
    private lateinit var txtConfirmPassword: PasswordField

    fun changeSceneToLogin() {
        val stage = btnLogin.scene.window as Stage
        stage.loadScene(LOGIN, WIDTH, HEIGHT) {
            it.title = "Login"
            it.isResizable = false
        }.show()
    }

    fun register() {
        //TODO filters
        if (userRepository.findByName(txtUsername.text).any()) return
        userRepository.add(
            User(
                txtUsername.text, txtEmail.text, txtPassword.text, Date(Date().time), Date(Date().time), null,
                emptyList()
            )
        )
    }
}