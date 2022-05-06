package com.example.myanimelist

import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.managers.SceneManager
import javafx.application.Application
import javafx.application.Application.launch
import javafx.stage.Stage
import java.sql.ResultSet
import java.sql.SQLException
import java.util.*
import kotlin.system.exitProcess

class MyAnimeListApplication : Application() {
    override fun start(stage: Stage) {
        val sceneManager = SceneManager
        sceneManager.setInstance(MyAnimeListApplication::class.java)
        sceneManager.initSplash(stage)
    }

}

fun main() {
    val dataBaseManager = DataBaseManager.getInstance()
        checkDataBase(dataBaseManager)
        launch(MyAnimeListApplication::class.java)
}


fun checkDataBase(db: DataBaseManager) {
    println("Comprobamos la conexión al Servidor BD")
    try {
        db.open()
        val rs: Optional<ResultSet> = db.select("SELECT 'Hello world'")
        if (rs.isPresent) {
            rs.get().next()
            db.close()
            println("Conexión correcta a la Base de Datos")
        }
    } catch (e: SQLException) {
        System.err.println("Error al conectar al servidor de Base de Datos: " + e.message)
        exitProcess(1)
    }
}
