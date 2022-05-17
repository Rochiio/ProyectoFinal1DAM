package com.example.myanimelist.controllers.inicio

import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.extensions.show
import com.example.myanimelist.filters.login.LoginFilters
import com.example.myanimelist.managers.DependenciesManager.getLoginFilter
import com.example.myanimelist.managers.SceneManager
import com.example.myanimelist.utils.*
import javafx.application.Platform
import javafx.scene.control.Alert
import javafx.stage.Stage


class LoginController : InicioController() {
    private var loginFilters: LoginFilters = getLoginFilter()


    fun openStageAbout() {
        val stage = Stage()
        stage.loadScene(ABOUT, ABOUT_WIDTH, ABOUT_HEIGHT) {
            it.title = "About"
            it.isResizable = false
            Platform.setImplicitExit(true)
            SceneManager.addIconStage(stage)
        }.show()

    }


    fun changeSceneToRegister() {
        val stage = btnRegister.scene.window as Stage
        stage.loadScene(REGISTER, WIDTH, HEIGHT) {
            it.title = "Registro"
            it.isResizable = false
        }.show()
    }


    private fun changeSceneToMain() {
        val stage = txtUsername.scene.window as Stage
        stage.loadScene(MAIN) {
            it.title = "Animes"
            it.isResizable = false
        }.show()
    }


    fun login() {
        val message = StringBuilder()
        if (!validateFields(message)) {
            Alert(Alert.AlertType.WARNING).show("Login invalid", message.toString())
            return
        }

        Alert(Alert.AlertType.INFORMATION).show("Login completed", "You will go to the main page")

        changeSceneToMain()
    }


    private fun validateFields(errorMessage: StringBuilder): Boolean {
        if (!loginFilters.checkUserCorrect(txtUsername.text, txtPassword.text))
            errorMessage.appendLine("Usuario no existe")

        return errorMessage.isEmpty()
    }
}