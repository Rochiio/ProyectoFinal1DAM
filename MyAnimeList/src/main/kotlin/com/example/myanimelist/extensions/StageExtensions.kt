package com.example.myanimelist.extensions

import com.example.myanimelist.MyAnimeListApplication
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

fun Stage.loadScene(
    scene: String,
    width: Double? = null,
    height: Double? = null,
    controller: Any? = null,
    action: Stage.() -> Unit
): Stage {
    val fxmlLoader = FXMLLoader(MyAnimeListApplication::class.java.getResource(scene))

    if (controller != null)
        fxmlLoader.setController(controller)

    val newScene: Scene = if (width != null && height != null)
        Scene(fxmlLoader.load(), width, height)
    else
        Scene(fxmlLoader.load())

    action(this)
    this.scene = newScene
    return this
}