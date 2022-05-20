package com.example.myanimelist.managers

import com.example.myanimelist.adapters.LocalDateTypeAdapter
import com.example.myanimelist.controllers.AnimeController
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
import com.example.myanimelist.service.backup.BackupStorage
import com.example.myanimelist.service.backup.IBackupStorage
import com.example.myanimelist.views.models.AnimeView
import com.example.myanimelist.service.img.IImgStorage
import com.example.myanimelist.service.img.ImgStorage
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.time.LocalDate

object DependenciesManager {
    //Singleton instances
    lateinit var globalUser : User
    lateinit var animeSelection : AnimeView
    private val dataBaseManager: DataBaseManager = DataBaseManager()
    private val gson: Gson = GsonBuilder().registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter()).create()
    private val usersRepository: IUsersRepository = UsersRepository(getDatabaseManager(), getLogger<UsersRepository>())
    private val animesRepository: IAnimeRepository = AnimeRepository(getDatabaseManager(), getLogger<AnimeRepository>())
    private val animeListRepository: IRepositoryAnimeList = AnimeListRepository(getDatabaseManager(),getLogger<AnimeListRepository>())
    private val imgStorage: IImgStorage = ImgStorage(getLogger<ImgStorage>())
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
    fun getAnimeListRepo(): IRepositoryAnimeList =animeListRepository
    @JvmStatic
    fun getLoginFilter(): LoginFilters = LoginFilters(getUsersRepo())

    @JvmStatic
    fun getAnimeController(): AnimeController = AnimeController()

    @JvmStatic
    fun getRegisterFilter(): RegisterFilters = RegisterFilters(getUsersRepo())

    @JvmStatic
    fun getBackupStorage(): IBackupStorage = BackupStorage(getGson())

    @JvmStatic
    fun getGson(): Gson = gson

    @JvmStatic
    fun getImgStorage(): IImgStorage = imgStorage

    inline fun <reified T> getLogger(): Logger =
        LogManager.getLogger(T::class.java)

    @JvmStatic
    fun <T> getLogger(clazz: Class<T>): Logger = LogManager.getLogger(clazz)

}