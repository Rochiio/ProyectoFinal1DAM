package com.example.myanimelist.controllers

import com.example.myanimelist.MyAnimeListApplication
import com.example.myanimelist.extensions.getLogger
import com.example.myanimelist.extensions.show
import com.example.myanimelist.utils.ThemesManager.getCurretnTheme
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.Alert
import javafx.scene.layout.Pane
import java.awt.Desktop
import java.io.IOException
import java.net.URI
import java.net.URISyntaxException


class AcercaDeController {
    private val desktop = Desktop.getDesktop()
    private val logger = getLogger<AcercaDeController>()

    @FXML
    var root: Pane? = null

    @FXML
    fun initialize() {
        root?.stylesheets?.clear()
        root?.stylesheets?.add(
            MyAnimeListApplication::class.java.getResource(getCurretnTheme().value)?.toString() ?: return
        )
    }


    @FXML
    private fun linkGitHub(event: ActionEvent) {
        val node = event.source as Node
        openPage(node.userData.toString())
    }


    /**
     * Para abrir la página web que queremos
     * @param page Página que queremos abrir
     */
    private fun openPage(page: String) {
        try {
            desktop.browse(URI(page))
        } catch (ex: IOException) {
            logger.error("error al abrir $page", ex)
            Alert(Alert.AlertType.ERROR).show("Error al abrir la página", "Contacte con el administrador")
        } catch (ex: URISyntaxException) {
            logger.error("error al abrir $page", ex)
            Alert(Alert.AlertType.ERROR).show("Error al abrir la página", "Contacte con el administrador")
        }
    }
}