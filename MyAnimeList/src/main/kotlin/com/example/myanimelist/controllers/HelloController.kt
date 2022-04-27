package com.example.myanimelist.controllers

import javafx.fxml.FXML
import javafx.scene.control.Label

class HelloController {
    @FXML
    lateinit var welcomeText: Label

    @FXML
    fun onHelloButtonClick() {
        welcomeText.text = "Welcome to JavaFX Application!"
    }
}