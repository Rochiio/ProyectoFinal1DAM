package com.example.myanimelist.utils

import com.example.myanimelist.MyAnimeListApplication
import javafx.scene.Node
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object ThemesManager {
    val logger : Logger = LogManager.getLogger(ThemesManager::class)

    /**
     * Cambia el modo entre oscuro y claro
     * @param Node un nodo de la interfaz
     * @param Themes el tema que vamos a aplicar
     */
    fun switchMode(node : Node, style : Themes){
        logger.info("Cambiando a modo oscuro")
        node.scene.root.stylesheets.clear()
        val dark : String = MyAnimeListApplication::class.java.getResource(style.value)!!.toString()
        node.scene.root.stylesheets.add(dark)
        logger.info("Cambio exitoso. Modo noche activado.")
    }

}