package com.example.myanimelist.managers

import com.example.myanimelist.adapters.LocalDateTypeAdapter
import com.example.myanimelist.controllers.anime.AnimeController
import com.example.myanimelist.filters.edition.EditFilters
import com.example.myanimelist.filters.login.LoginFilters
import com.example.myanimelist.filters.login.RegisterFilters
import com.example.myanimelist.manager.DataBaseManager
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
import com.example.myanimelist.views.models.AnimeView
import com.example.myanimelist.views.models.UserView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.time.LocalDate

object DependenciesManager {
    //If its testing put to true
    var isTesting: Boolean = false

    //Singleton instances
    lateinit var globalUser: User
    private val dataBaseManager: DataBaseManager by lazy {
        if (isTesting)
            DataBaseManager("animeDev.db")
        else
            DataBaseManager("anime.db")
    }
    lateinit var animeSelection: AnimeView
    lateinit var userSelectionAdmin: UserView
    private val gson: Gson =
        GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter()).create()
    private val usersRepository: IUsersRepository = UsersRepository(getDatabaseManager(), getLogger<UsersRepository>())
    private val animesRepository: IAnimeRepository = AnimeRepository(getDatabaseManager(), getLogger<AnimeRepository>())
    private val imgStorage: IImgStorage = ImgStorage(getLogger<ImgStorage>())
    private val animeListRepository: IRepositoryAnimeList =
        AnimeListRepository(getDatabaseManager(), getLogger<AnimeListRepository>(), getUsersRepo())
    private val reviewsRepository: IRepositoryReview = ReviewsRepository(
        getDatabaseManager(), getAnimesRepo(),
        getUsersRepo(),
        getLogger<ReviewsRepository>()
    )

    //Factories
    @JvmStatic
    fun getDatabaseManager(): DataBaseManager = dataBaseManager


    @JvmStatic
    fun getUsersRepo(): IUsersRepository = usersRepository

    @JvmStatic
    fun getAnimesRepo(): IAnimeRepository = animesRepository

    @JvmStatic
    fun getReviewsRepo(): IRepositoryReview = reviewsRepository

    @JvmStatic
    fun getAnimeListRepo(): IRepositoryAnimeList = animeListRepository

    @JvmStatic
    fun getLoginFilter(): LoginFilters = LoginFilters(getUsersRepo())

    @JvmStatic
    fun getAnimeStorage(): IAnimeStorage = AnimeStorage()

    @JvmStatic
    fun getEditFilter(): EditFilters = EditFilters()

    @JvmStatic
    fun getAnimeController(): AnimeController = AnimeController()

    @JvmStatic
    fun getRegisterFilter(): RegisterFilters = RegisterFilters(getUsersRepo())

    @JvmStatic
    fun getBackupStorage(): IBackupStorage = BackupStorage(getGson())

    @JvmStatic
    fun getImgStorage(): IImgStorage = ImgStorage(getLogger<ImgStorage>())

    @JvmStatic
    fun getGson(): Gson = gson

    inline fun <reified T> getLogger(): Logger =
        LogManager.getLogger(T::class.java)

    @JvmStatic
    fun <T> getLogger(clazz: Class<T>): Logger = LogManager.getLogger(clazz)

}