package com.example.myanimelist

import com.example.myanimelist.managers.SceneManager

import javafx.application.Application
import javafx.application.Application.launch
import javafx.stage.Stage
import org.koin.core.context.startKoin

class MyAnimeListApplication : Application() {
    override fun start(stage: Stage) {
        val sceneManager = SceneManager
        sceneManager.setInstance(MyAnimeListApplication::class.java)
        sceneManager.initSplash(stage)
    }

}

fun main() {


    launch(MyAnimeListApplication::class.java)
}
//checkDataBase(get(_root_ide_package_.com.example.myanimelist.manager.DataBaseManager::class.java))

//}


