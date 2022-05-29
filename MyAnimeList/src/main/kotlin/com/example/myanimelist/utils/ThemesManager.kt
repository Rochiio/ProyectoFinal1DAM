package com.example.myanimelist.utils

import com.example.myanimelist.MyAnimeListApplication
import javafx.scene.Node
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object ThemesManager {
    val logger: Logger = LogManager.getLogger(ThemesManager::class)
    var currentTheme: Themes = Themes.CLARO


    fun getCurretnTheme(): Themes {
        return currentTheme
    }

    /**
     * Cambia el modo entre oscuro y claro
     * @param Node un nodo de la interfaz
     * @param Themes el tema que vamos a aplicar
     */
    fun switchMode(node: Node, style: Themes) {
        logger.info("Cambiando a modo oscuro")
        node.scene.root.stylesheets.clear()
        val dark: String = MyAnimeListApplication::class.java.getResource(style.value)!!.toString()
        node.scene.root.stylesheets.add(dark)
        logger.info("Cambio exitoso. Modo noche activado.")
    }

    fun setTheme(node: Node) {
        //val sc = node.scene
        //val r = sc.root
        node.scene.root.stylesheets.clear()
        val theme: String = MyAnimeListApplication::class.java.getResource(currentTheme.value)!!.toString()
        node.scene.root.stylesheets.add(theme)

    }

    fun changeTheme() {
        currentTheme = if (currentTheme == Themes.CLARO)
            Themes.OSCURO
        else
            Themes.CLARO


    }
}