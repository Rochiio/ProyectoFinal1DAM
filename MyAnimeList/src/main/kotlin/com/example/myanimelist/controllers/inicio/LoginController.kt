package com.example.myanimelist.controllers.inicio

import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.extensions.show
import com.example.myanimelist.filters.login.LoginFilters
import com.example.myanimelist.managers.CurrentUser
import com.example.myanimelist.managers.ResourcesManager
import com.example.myanimelist.managers.SceneManager
import com.example.myanimelist.utils.*
import javafx.scene.control.Alert
import javafx.scene.image.Image
import javafx.stage.Stage
import org.koin.core.component.inject


class LoginController : InicioController() {
    private val loginFilters: LoginFilters by inject()
    private val user: CurrentUser by inject()


    /**
     * Abrir la escena del Acerca De
     */
    fun openStageAbout() = SceneManager.openStageAbout()


    /**
     * Cambiar la escena al registro
     */
    fun changeSceneToRegister() {
        val stage = btnRegister.scene.window as Stage
        stage.loadScene(REGISTER, WIDTH, HEIGHT) {
            title = "Registro"
            isResizable = false
            icons.add(Image(ResourcesManager.getIconOf("icono.png")))
        }.show()
    }


    /**
     * Cambiar la escena al main, dependiendo del usuario se abre un tipo de escena u otra
     */
    private fun changeSceneToMain() {
        user.value =
            userRepository.findByName(txtUsername.text).first { it.name == txtUsername.text }

        val stage = txtUsername.scene.window as Stage


        if (!user.isAdmin) {
            stage.loadScene(MAIN_USER_MYLIST, WIDTH, HEIGHT, isMainScene = true) {
                title = "Animes"
                isResizable = false
                icons.add(Image(ResourcesManager.getIconOf("icono.png")))
            }.show()
        } else {
            stage.loadScene(MAIN_ADMIN, WIDTH, HEIGHT, isMainScene = true) {
                title = "Animes"
                isResizable = false
                icons.add(Image(ResourcesManager.getIconOf("icono.png")))
            }.show()
        }
    }


    /**
     * Realizar el login
     */
    fun login() {
        val message = StringBuilder()
        if (!validateFields(message)) {
            Alert(Alert.AlertType.WARNING).show("Loggeo invalido", message.toString())
            return
        }

        changeSceneToMain()
    }


    /**
     * Validación del Login
     * @param errorMessage por si no pasase los filtros, mostrar el error
     * @return boolean dependiendo de si pasa los filtros correctamente o no
     */
    private fun validateFields(errorMessage: StringBuilder): Boolean {
        if (!loginFilters.checkUserCorrect(txtUsername.text, txtPassword.text)) {
            errorMessage.appendLine("Usuario ${txtUsername.text} incorrecto")
        }
        return errorMessage.isEmpty()
    }
}