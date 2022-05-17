package com.example.myanimelist.managers

import com.example.myanimelist.filters.login.LoginFilters
import com.example.myanimelist.filters.login.RegisterFilters
import com.example.myanimelist.manager.DataBaseManager
import com.example.myanimelist.models.User
import com.example.myanimelist.repositories.admins.AdminRepository
import com.example.myanimelist.repositories.admins.IAdminRepository
import com.example.myanimelist.repositories.animes.AnimeRepository
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.repositories.reviews.IRepositoryReview
import com.example.myanimelist.repositories.reviews.ReviewsRepository
import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.repositories.users.UsersRepository
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object DependenciesManager {
    //Singleton instances
    lateinit var globalUser : User
    private val dataBaseManager: DataBaseManager = DataBaseManager()
    private val adminsRepository: IAdminRepository = AdminRepository(getDatabaseManager(), getLogger<AdminRepository>())
    private val usersRepository: IUsersRepository = UsersRepository(getDatabaseManager(), getLogger<UsersRepository>())
    private val animesRepository: IAnimeRepository = AnimeRepository(getDatabaseManager(), getLogger<AnimeRepository>())
    private val reviewsRepository: IRepositoryReview = ReviewsRepository(
        getDatabaseManager(), getAnimesRepo(),
        getUsersRepo(),
        getLogger<ReviewsRepository>()
    )

    //Factories
    @JvmStatic
    fun getDatabaseManager(): DataBaseManager = dataBaseManager

    @JvmStatic
    fun getAdminsRepo(): IAdminRepository = adminsRepository

    @JvmStatic
    fun getUsersRepo(): IUsersRepository = usersRepository

    @JvmStatic
    fun getAnimesRepo(): IAnimeRepository = animesRepository

    @JvmStatic
    fun getReviewsRepo(): IRepositoryReview = reviewsRepository

    @JvmStatic
    fun getLoginFilter(): LoginFilters = LoginFilters(getUsersRepo())

    @JvmStatic
    fun getRegisterFilter(): RegisterFilters = RegisterFilters(getUsersRepo())

    inline fun <reified T> getLogger(): Logger =
        LogManager.getLogger(T::class.java)

    @JvmStatic
    fun <T> getLogger(clazz: Class<T>): Logger = LogManager.getLogger(clazz)
}