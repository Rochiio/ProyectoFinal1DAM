package com.example.myanimelist.controllers.main.admin

import com.example.myanimelist.dto.LoadDTO
import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.managers.ResourcesManager
import com.example.myanimelist.service.txt.TxtBackup
import com.example.myanimelist.utils.*
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.stage.Stage

class AdminController {
    @FXML
    lateinit var adminAnimeButton: Button

    @FXML
    lateinit var userAdminButton: Button

    @FXML
    fun initialize(){

        val loadDTO = LoadDTO(
            true,
            (ThemesManager.currentTheme == Themes.OSCURO)
        )
        TxtBackup().save(loadDTO)

    }


    /**
     * Cambiar escena a la lista de todos los animes
     */
    fun changeStageToAnimes() {
        Stage().loadScene(MAIN_USER_ANIME, WIDTH, HEIGHT) {
            title = "Animes"
            isResizable = false
            icons.add(Image(ResourcesManager.getIconOf("icono.png")))
        }.show()
    }


    /**
     * Cambiar la escena a la lista de usuarios
     */
    fun changeStageToUsers() {
        Stage().loadScene(ADMIN_USERS_LIST, WIDTH, HEIGHT) {
            title = "Users"
            isResizable = false
            icons.add(Image(ResourcesManager.getIconOf("icono.png")))
        }.show()
    }

}