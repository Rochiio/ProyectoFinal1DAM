package com.example.myanimelist.controllers.main.user

import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.managers.ResourcesManager
import com.example.myanimelist.utils.MAIN_USER_ANIME
import com.example.myanimelist.utils.MAIN_USER_MYLIST
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.stage.Stage

class MainUserMenu {


    @FXML
    private lateinit var userMyListButton : Button

    @FXML
    private lateinit var userCatalogoButton : Button


    fun changeStageToMyList(actionEvent: ActionEvent) {
        val stage = Stage()
        stage.loadScene(MAIN_USER_MYLIST) {
            title = "Añadir anime"
            isResizable = false
            icons.add(Image(ResourcesManager.getIconOf("icono.png")))
        }.show()

    }

    fun changeStageToAnimes(actionEvent: ActionEvent) {
        val stage = Stage()
        stage.loadScene(MAIN_USER_ANIME) {
            title = "Añadir anime"
            isResizable = false
            icons.add(Image(ResourcesManager.getIconOf("icono.png")))
        }.show()

    }
}