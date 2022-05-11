package com.example.myanimelist.managers

import com.example.myanimelist.utils.ViewConfig
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import javafx.stage.StageStyle
import java.io.IOException
import java.util.*

object SceneManager {
    var instance: SceneManager? = null
    lateinit var appClass: Class<*>
    private var mainStage: Stage? = null

    fun setInstance(appClass: Class<*>){
        this.appClass = appClass
    }

    fun get(): SceneManager? {
        return instance
    }


    @Throws(IOException::class, InterruptedException::class)
    fun initSplash(stage: Stage) {
        Platform.setImplicitExit(false)
        println("Iniciando Splash")
        val fxmlLoader = FXMLLoader(appClass.getResource("views/splash-view.fxml"))
        val scene = Scene(fxmlLoader.load())
        addIconStage(stage)
        stage.title = "Splash"
        stage.isResizable = false
        stage.scene = scene
        stage.initStyle(StageStyle.TRANSPARENT)
        stage.show()
    }


    @Throws(IOException::class)
    fun initMain() {
        Platform.setImplicitExit(true)
        val fxmlLoader = FXMLLoader(Objects.requireNonNull(appClass.getResource("views/inicioSesion-view.fxml")))
        val scene = Scene(fxmlLoader.load(), ViewConfig.WIDTH.value.toDouble(), ViewConfig.HEIGHT.value.toDouble())
        val stage = Stage()
        addIconStage(stage)
        stage.isResizable = false
        stage.title = "Login"
        stage.initStyle(StageStyle.DECORATED)
        println("Inicio listo")
        stage.scene = scene
        mainStage = stage
        stage.show()
    }


    @Throws(IOException::class)
    fun changeScene(node: Node, view: String?) {
        println("Loading scene ")
        val stage = node.scene.window as Stage
        //oldStage.hide(); // Oculto la anterior
        val root = FXMLLoader.load<Parent>(
            Objects.requireNonNull(
                appClass.getResource(view)
            )
        )
        val newScene = Scene(root, 600.0, 450.0)
        println("Scene loaded")
        stage.scene = newScene
        stage.show()
    }


    /**
     * Para añadir el icono al crear stage más rápido
     * @param stage escena a añadir el icono
     */
    private fun addIconStage(stage: Stage){
        stage.icons.add(Image(appClass.getResourceAsStream(ViewConfig.ICON.value)))
    }
}