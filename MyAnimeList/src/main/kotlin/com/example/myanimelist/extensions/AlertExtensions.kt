package com.example.myanimelist.extensions

import javafx.scene.control.Alert
import javafx.scene.control.ButtonType

fun Alert.show(title: String, message: String): ButtonType? {
    this.title = title
    this.contentText = message
    return this.showAndWait().orElse(null)
}