package com.example.myanimelist

import com.example.myanimelist.dto.AnimeDTO
import com.example.myanimelist.dto.LoadDTO
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.managers.SceneManager
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.service.anime.IAnimeStorage
import com.example.myanimelist.service.txt.TxtBackup
import com.example.myanimelist.utils.Themes
import com.example.myanimelist.utils.ThemesManager
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
    val loadData = TxtBackup().load()
    if(loadData.get().isLoaded) {
        ThemesManager.currentTheme = if (loadData.get().isNightMode)
            Themes.OSCURO
        else
            Themes.CLARO
        return
    }
    val listAnimes: MutableList<AnimeDTO> =
        animeStorage.load().orElse(null) ?: throw Exception("listanimes is null")
    for (item in listAnimes) {
        animeRepository.add(item.fromDTO())
    }
    TxtBackup().save(
        LoadDTO(true, false)
    )
    return
}

//checkDataBase(get(_root_ide_package_.com.example.myanimelist.manager.DataBaseManager::class.java))

//}


