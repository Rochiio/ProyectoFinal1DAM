package com.example.myanimelist.services

import com.example.myanimelist.dto.BackupDTO
import com.example.myanimelist.modules.repositoryModule
import com.example.myanimelist.repositories.admins.IAdminRepository
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.repositories.reviews.IRepositoryReview
import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.service.backup.IBackupStorage
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.test.inject
import org.koin.test.junit5.AutoCloseKoinTest

class BackupStorageTest : AutoCloseKoinTest() {
    private val storage: IBackupStorage by inject()
    private val animesRepo: IAnimeRepository by inject()
    private val usersRepo: IUsersRepository by inject()
    private val reviewRepo: IRepositoryReview by inject()
    private val adminRepo: IAdminRepository by inject()

    init {
        startKoin { modules(repositoryModule) }
    }

    @Test
    @Order(0)
    fun save() {
        val dto = BackupDTO(usersRepo.findAll(), reviewRepo.findAll(), animesRepo.findAll(), adminRepo.findAll())
        storage.save(dto)
    }

    @Test
    @Order(1)
    fun load() {
        storage.load()
    }


}