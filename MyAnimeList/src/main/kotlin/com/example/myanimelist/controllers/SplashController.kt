package com.example.myanimelist.controllers

import com.example.myanimelist.extensions.getLogger
import com.example.myanimelist.managers.SceneManager
import com.example.myanimelist.utils.Properties.RESOURCES_IMAGES_DIR
import javafx.animation.FadeTransition
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.stage.Stage
import javafx.util.Duration
import org.apache.logging.log4j.Logger
import java.io.FileInputStream
import java.io.IOException
import java.net.URL
import java.util.*

class SplashController : Initializable {

    private val logger: Logger = getLogger<SplashController>()

    @FXML
    lateinit var fondo: ImageView
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        fondo.image = Image(FileInputStream(randomImg()))
        // Una animación de fondo
        val transition = FadeTransition(Duration.millis(5000.0), fondo)
        transition.fromValue = 1.0
        transition.toValue = 1.0
        transition.play()


        transition.onFinished = EventHandler {
            // Nos cerramos
            val ventana = fondo.scene.window as Stage
            ventana.hide()
            // Mostramos la ventana principal
            val sceneManager = SceneManager
            try {
                sceneManager.initMain()
            } catch (e: IOException) {
                logger.error(e)
            }
        }
    }


    /**
     * Elegir imagen de splash aleatoria
     * @return imagen aleatoria
     */
    private fun randomImg(): String {
        val rNum = (1..6).random()
        return RESOURCES_IMAGES_DIR + "/splash$rNum.png"
    }
}