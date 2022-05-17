package com.example.myanimelist.services

import com.example.myanimelist.dto.BackupDTO
import com.example.myanimelist.manager.DataBaseManager
import com.example.myanimelist.managers.DependenciesManager
//import com.example.myanimelist.modules.repositoryModule
import com.example.myanimelist.repositories.admins.IAdminRepository
import com.example.myanimelist.repositories.animes.AnimeRepository
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.repositories.reviews.IRepositoryReview
import com.example.myanimelist.repositories.reviews.ReviewsRepository
import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.repositories.users.UsersRepository
import com.example.myanimelist.service.backup.BackupStorage
import com.example.myanimelist.service.backup.IBackupStorage
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test


class BackupStorageTest  {
    private   var storage: IBackupStorage  = BackupStorage()
    private  var animesRepo: IAnimeRepository = DependenciesManager.getAnimesRepo()
    private  var usersRepo: IUsersRepository = DependenciesManager.getUsersRepo()
    private  var reviewRepo: IRepositoryReview = DependenciesManager.getReviewsRepo()



    @Test
    @Order(0)
    fun save() {
        val dto = BackupDTO(usersRepo.findAll(), reviewRepo.findAll(), animesRepo.findAll())
        storage.save(dto)
    }

    @Test
    @Order(1)
    fun load() {
        storage.load()
    }


}