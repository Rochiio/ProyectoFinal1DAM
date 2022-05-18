package com.example.myanimelist.extensions

import javafx.scene.control.Alert

fun Alert.show(title: String, message: String) {
    this.title = title
    this.contentText = message
    this.showAndWait()
}