package com.example.myanimelist.controllers.main.admin

import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.managers.ResourcesManager
import com.example.myanimelist.utils.HEIGHT
import com.example.myanimelist.utils.MAIN_ADMIN
import com.example.myanimelist.utils.MAIN_USER_ANIME
import com.example.myanimelist.utils.WIDTH
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.stage.Stage

class AdminController {
    @FXML
    lateinit var adminAnimeButton : Button
    @FXML
    lateinit var userAdminButton : Button

    fun changeStageToAnimes(actionEvent: ActionEvent) {
        Stage().loadScene(MAIN_USER_ANIME, WIDTH, HEIGHT) {
            title = "Animes"
            isResizable = false
            icons.add(Image(ResourcesManager.getIconOf("icono.png")))
        }.show()
    }

    fun changeStageToUsers(actionEvent: ActionEvent) {
        Stage().loadScene(MAIN_ADMIN, WIDTH, HEIGHT) {
            title = "Users"
            isResizable = false
            icons.add(Image(ResourcesManager.getIconOf("icono.png")))
        }.show()
    }

}