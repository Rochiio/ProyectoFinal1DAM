package com.example.myanimelist.managers

import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.utils.*
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.stage.StageStyle
import java.io.IOException

object SceneManager {
    lateinit var appClass: Class<*>
    private var mainStage: Stage? = null

    inline fun <reified T> setAppClass() {
        this.appClass = T::class.java
    }

    //Si se usa mas de una vez dejar openStageAbout aqui o en un utils
    fun openStageAbout() =
        Stage().loadScene(ABOUT, ABOUT_WIDTH, ABOUT_HEIGHT) {
            title = "About"
            isResizable = false
            initModality(Modality.APPLICATION_MODAL)
            addIconStage(this)
        }.show()

    @Throws(IOException::class, InterruptedException::class)
    fun initSplash(stage: Stage) {
        Platform.setImplicitExit(false)
        println("Iniciando Splash")
        stage.loadScene(SPLASH) {
            addIconStage(stage)
            stage.title = "Splash"
            stage.isResizable = false
            stage.initStyle(StageStyle.TRANSPARENT)
        }.show()
    }


    @Throws(IOException::class)
    fun initMain() {
        Platform.setImplicitExit(true)
        mainStage = Stage().loadScene(LOGIN, WIDTH, HEIGHT) {
            isResizable = false
            title = "Login"
            initStyle(StageStyle.DECORATED)
            addIconStage(this)
        }
        println("Inicio listo")
        mainStage?.show()
    }


    @Throws(IOException::class)
    fun changeScene(node: Node, view: String) {
        println("Loading scene ")
        val stage = node.scene.window as Stage
        //oldStage.hide(); // Oculto la anterior
        val root = FXMLLoader.load<Parent>(
            appClass.getResource(view) ?: throw Exception("")

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
    fun addIconStage(stage: Stage) = stage.icons.add(Image(ResourcesManager.getIconOf("icono.png")))

}