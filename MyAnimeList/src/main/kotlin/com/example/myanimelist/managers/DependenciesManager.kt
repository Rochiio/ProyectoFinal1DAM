package com.example.myanimelist.managers

import com.example.myanimelist.adapters.LocalDateTypeAdapter
import com.example.myanimelist.controllers.anime.AnimeController
import com.example.myanimelist.filters.edition.EditFilters
import com.example.myanimelist.filters.login.LoginFilters
import com.example.myanimelist.filters.login.RegisterFilters
import com.example.myanimelist.models.User
import com.example.myanimelist.repositories.animeList.AnimeListRepository
import com.example.myanimelist.repositories.animeList.IRepositoryAnimeList
import com.example.myanimelist.repositories.animes.AnimeRepository
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.repositories.reviews.IRepositoryReview
import com.example.myanimelist.repositories.reviews.ReviewsRepository
import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.repositories.users.UsersRepository
import com.example.myanimelist.service.anime.AnimeStorage
import com.example.myanimelist.service.anime.IAnimeStorage
import com.example.myanimelist.service.backup.BackupStorage
import com.example.myanimelist.service.backup.IBackupStorage
import com.example.myanimelist.service.img.IImgStorage
import com.example.myanimelist.service.img.ImgStorage
import com.example.myanimelist.utils.html.GeneratorHtml
import com.example.myanimelist.utils.html.GeneratorHtmlStats
import com.example.myanimelist.views.models.AnimeView
import com.example.myanimelist.views.models.UserView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import java.time.LocalDate

object DependenciesManager : KoinComponent {
    //If its testing put to true
    var isTesting: Boolean = false

    //Singleton instances
    lateinit var globalUser: User
    lateinit var animeSelection: AnimeView

    private val gson: Gson =
        GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter()).create()
    fun getLoginFilter(): LoginFilters = LoginFilters(get())

    fun getAnimeStorage(): IAnimeStorage = get()

    fun getEditFilter(): EditFilters = EditFilters()

    fun getAnimeController(): AnimeController = AnimeController()

    fun getRegisterFilter(): RegisterFilters = RegisterFilters(get())

    fun getBackupStorage(): IBackupStorage = BackupStorage(getGson())

    fun getImgStorage(): IImgStorage = get()

    fun getGson(): Gson = gson

    fun getHtmlGenerator():GeneratorHtml = get()

    inline fun <reified T> getLogger(): Logger =
        LogManager.getLogger(T::class.java)

    fun <T> getLogger(clazz: Class<T>): Logger = LogManager.getLogger(clazz)

}