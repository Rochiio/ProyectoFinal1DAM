package com.example.myanimelist

import com.example.myanimelist.managers.SceneManager
import com.example.myanimelist.modules.controllerModules
import com.example.myanimelist.modules.repositoryModule
import com.example.myanimelist.modules.servicesModules
import javafx.application.Application
import javafx.application.Application.launch
import javafx.stage.Stage
import org.koin.core.context.startKoin

class MyAnimeListApplication : Application() {
    override fun start(stage: Stage) {
        val sceneManager = SceneManager
        sceneManager.setAppClass<MyAnimeListApplication>()
        sceneManager.initSplash(stage)
    }

}

fun main() {

    startKoin {
        modules(repositoryModule, controllerModules, servicesModules)
    }
    launch(MyAnimeListApplication::class.java)
}
//checkDataBase(get(_root_ide_package_.com.example.myanimelist.manager.DataBaseManager::class.java))

//}


