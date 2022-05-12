package com.example.myanimelist.controllers.inicio

import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.utils.HEIGHT
import com.example.myanimelist.utils.REGISTER
import com.example.myanimelist.utils.WIDTH
import javafx.stage.Stage

class LoginController : InicioController() {
    fun changeSceneToRegister() {
        val stage = btnRegister.scene.window as Stage
        stage.loadScene(REGISTER, WIDTH, HEIGHT) {
            it.title = "Registro"
            it.isResizable = false
        }.show()
    }

    fun login() {
        //TODO filters
        if (!userRepository.findByName(txtUsername.text).any { it.password == txtPassword.text }) return
    }
}