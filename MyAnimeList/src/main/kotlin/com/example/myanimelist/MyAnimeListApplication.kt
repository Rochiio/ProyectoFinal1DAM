package com.example.myanimelist

import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.managers.SceneManager
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.service.anime.IAnimeStorage
import javafx.application.Application
import javafx.application.Application.launch
import javafx.stage.Stage


class MyAnimeListApplication : Application() {
    override fun start(stage: Stage) {
        val sceneManager = SceneManager
        sceneManager.setAppClass<MyAnimeListApplication>()
        sceneManager.initSplash(stage)
    }

}
var animeRepository: IAnimeRepository = DependenciesManager.getAnimesRepo()
var animeStorage: IAnimeStorage = DependenciesManager.getAnimeStorage()


fun main() {
    initAnimes()
    launch(MyAnimeListApplication::class.java)
}

fun initAnimes() {
     val listAnimes = animeStorage.load()
    if(listAnimes.isPresent){
        for(item in listAnimes.get()){
            animeRepository.add(item.fromDTO())
        }
    }

}

//checkDataBase(get(_root_ide_package_.com.example.myanimelist.manager.DataBaseManager::class.java))

//}


