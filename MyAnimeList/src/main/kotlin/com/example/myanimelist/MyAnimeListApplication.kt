package com.example.myanimelist

import com.example.myanimelistjava.managers.DataBaseManager
import com.example.myanimelist.managers.SceneManager
import com.example.myanimelist.modules.RepositoriesModules.repositoryModule
import javafx.application.Application
import javafx.application.Application.launch
import javafx.stage.Stage
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.get
import java.sql.SQLException
import kotlin.system.exitProcess

class MyAnimeListApplication : Application() {

    override fun start(stage: Stage) {
        val sceneManager = SceneManager
        sceneManager.setInstance(MyAnimeListApplication::class.java)
        sceneManager.initSplash(stage)
    }

}

fun main() {
    startKoin {
        modules(repositoryModule)
    }

    checkDataBase(get(_root_ide_package_.com.example.myanimelistjava.managers.DataBaseManager::class.java))

    launch(MyAnimeListApplication::class.java)


}


fun checkDataBase(db: _root_ide_package_.com.example.myanimelistjava.managers.DataBaseManager) {
    println("Comprobamos la conexión al Servidor BD")
    try {
        db.open()
        val rs = db.select("SELECT 'Hello world'")
        rs.next()
        db.close()
        println("Conexión correcta a la Base de Datos")
    } catch (e: SQLException) {
        System.err.println("Error al conectar al servidor de Base de Datos: " + e.message)
        exitProcess(1)
    }
}
