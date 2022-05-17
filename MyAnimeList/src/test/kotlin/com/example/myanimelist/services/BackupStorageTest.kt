package com.example.myanimelist.services

import com.example.myanimelist.dto.BackupDTO
import com.example.myanimelist.manager.DataBaseManager
//import com.example.myanimelist.modules.repositoryModule
import com.example.myanimelist.repositories.admins.IAdminRepository
import com.example.myanimelist.repositories.animes.AnimeRepository
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.repositories.reviews.IRepositoryReview
import com.example.myanimelist.repositories.reviews.ReviewsRepository
import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.repositories.users.UsersRepository
import com.example.myanimelist.service.backup.IBackupStorage
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test


class BackupStorageTest  {
    private lateinit var storage: IBackupStorage
    private  var animesRepo: IAnimeRepository = AnimeRepository(DataBaseManager.getInstance())
    private  var usersRepo: IUsersRepository = UsersRepository(DataBaseManager.getInstance())
    private  var reviewRepo: IRepositoryReview = ReviewsRepository(DataBaseManager.getInstance(), animesRepo, usersRepo)



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