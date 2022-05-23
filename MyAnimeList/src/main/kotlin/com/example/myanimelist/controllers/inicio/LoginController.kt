package com.example.myanimelist.controllers.inicio

import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.extensions.show
import com.example.myanimelist.filters.login.LoginFilters
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.managers.DependenciesManager.getLoginFilter
import com.example.myanimelist.managers.ResourcesManager
import com.example.myanimelist.managers.SceneManager
import com.example.myanimelist.utils.*
import javafx.scene.control.Alert
import javafx.scene.image.Image
import javafx.stage.Stage


class LoginController : InicioController() {
    private var loginFilters: LoginFilters = getLoginFilter()


    fun openStageAbout() = SceneManager.openStageAbout()


    fun changeSceneToRegister() {
        val stage = btnRegister.scene.window as Stage
        stage.loadScene(REGISTER, WIDTH, HEIGHT) {
            title = "Registro"
            isResizable = false
            icons.add(Image(ResourcesManager.getIconOf("icono.png")))
        }.show()
    }


    private fun changeSceneToMain() {
        DependenciesManager.globalUser =
            userRepository.findByName(txtUsername.text).first { it.name == txtUsername.text }
        DependenciesManager.globalUser.myList.addAll(animesUserList.findByUserId(DependenciesManager.globalUser))

        val stage = txtUsername.scene.window as Stage


        if(!DependenciesManager.globalUser.admin) {
            stage.loadScene(MAIN_USER_MYLIST,WIDTH, HEIGHT) {
                title = "Animes"
                isResizable = false
                icons.add(Image(ResourcesManager.getIconOf("icono.png")))
            }.show()
        }else{
            stage.loadScene(MAIN_ADMIN,WIDTH, HEIGHT) {
                title = "Animes"
                isResizable = false
                icons.add(Image(ResourcesManager.getIconOf("icono.png")))
            }.show()
        }
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
        if(!loginFilters.checkUserCorrect(txtUsername.text)) {
            errorMessage.appendLine("Usuario ${txtUsername.text} incorrecto")
            return false
        }
        if(!loginFilters.checkPasswordCorrect(txtUsername.text,txtPassword.text)){
            errorMessage.appendLine("Contraseña incorrecta")
            return false
        }
        return true
    }
}