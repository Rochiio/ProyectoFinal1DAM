package com.example.myanimelist

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

    }

    //checkDataBase(get(_root_ide_package_.com.example.myanimelistjava.managers.DataBaseManager::class.java))



}


