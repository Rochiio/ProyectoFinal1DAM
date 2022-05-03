package com.example.myanimelist

import com.example.myanimelist.managers.SceneManager
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import java.sql.ResultSet
import java.sql.SQLException
import java.util.*

class MyAnimeListApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(MyAnimeListApplication::class.java.getResource("views/splash-view.fxml"))
        val scene = Scene(fxmlLoader.load(), 320.0, 240.0)
        stage.title = "Hello!"
        stage.scene = scene
        stage.show()
    }


}

fun main() {
    val db = DataBaseManager.getInstance()
    val  sceneManager = SceneManager.getInstance()
        Application.launch(MyAnimeListApplication::class.java)
        checkDataBase(db);

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
        System.exit(1)
    }
}
