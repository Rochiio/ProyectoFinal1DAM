package com.example.myanimelist.services

//import com.example.myanimelist.modules.repositoryModule
import com.example.myanimelist.dto.BackupDTO
import com.example.myanimelist.repositories.admins.IAdminRepository
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.repositories.reviews.IRepositoryReview
import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.service.backup.IBackupStorage
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import javax.inject.Inject

class BackupStorageTest {
    @Inject
    private lateinit var storage: IBackupStorage

    @Inject
    private lateinit var animesRepo: IAnimeRepository

    @Inject
    private lateinit var usersRepo: IUsersRepository

    @Inject
    private lateinit var reviewRepo: IRepositoryReview

    @Inject
    private lateinit var adminRepo: IAdminRepository


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