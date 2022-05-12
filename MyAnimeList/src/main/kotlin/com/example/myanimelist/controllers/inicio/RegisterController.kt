package com.example.myanimelist.controllers.inicio

import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.utils.HEIGHT
import com.example.myanimelist.utils.LOGIN
import com.example.myanimelist.utils.WIDTH
import javafx.fxml.FXML
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.stage.Stage

class RegisterController(repository: IUsersRepository) : InicioController(repository) {
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
        //userRepository.add(User(txtUsername, txtEmail, txtPassword, Date()))
    }
}