package com.example.myanimelist.extensions

import com.example.myanimelist.MyAnimeListApplication
import com.example.myanimelist.utils.HEIGHT
import com.example.myanimelist.utils.WIDTH
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

fun Stage.loadScene(
    scene: String,
    width: Double? = null,
    height: Double? = null,
    action: (options: Stage) -> Unit
): Stage {
    val fxmlLoader = FXMLLoader(MyAnimeListApplication::class.java.getResource(scene))

    val newScene: Scene = if (width != null && height != null)
        Scene(fxmlLoader.load(), WIDTH, HEIGHT)
    else
        Scene(fxmlLoader.load())

    action(this)
    this.scene = newScene
    return this
}