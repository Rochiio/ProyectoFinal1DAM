package com.example.myanimelist

import com.example.myanimelist.di.startAllModules
import com.example.myanimelist.dto.LoadDTO
import com.example.myanimelist.managers.SceneManager
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.service.anime.IAnimeStorage
import com.example.myanimelist.service.txt.TxtBackup
import com.example.myanimelist.utils.Themes
import com.example.myanimelist.utils.ThemesManager
import javafx.application.Application
import javafx.application.Application.launch
import javafx.stage.Stage
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class MyAnimeListApplication : Application(), KoinComponent {
    private val animeRepository: IAnimeRepository by inject()
    private val animeStorage: IAnimeStorage by inject()
    override fun start(stage: Stage) {

        initAnimes(animeRepository, animeStorage)
        val sceneManager = SceneManager
        sceneManager.setAppClass<MyAnimeListApplication>()
        sceneManager.initSplash(stage)
    }

    companion object {
        fun onExit() {
            val loadDTO = LoadDTO(isLoaded = true, isNightMode = ThemesManager.currentTheme == Themes.OSCURO)
            TxtBackup().save(loadDTO)
        }
    }

}


fun main() {
    startAllModules()
    launch(MyAnimeListApplication::class.java)
}

fun initAnimes(animeRepository: IAnimeRepository, animeStorage: IAnimeStorage) {
    val loadData = TxtBackup().load()
    if (loadData?.isLoaded == true) {
        ThemesManager.currentTheme = if (loadData.isNightMode)
            Themes.OSCURO
        else
            Themes.CLARO
        return
    }
    val listAnimes =
        animeStorage.load() ?: throw Exception("listanimes is null")
    for (item in listAnimes) {
        animeRepository.add(item.fromDTO())
    }
    TxtBackup().save(
        LoadDTO(isLoaded = true, isNightMode = false)
    )
    return
}

//checkDataBase(get(_root_ide_package_.com.example.myanimelist.managers.DataBaseManager::class.java))

//}


