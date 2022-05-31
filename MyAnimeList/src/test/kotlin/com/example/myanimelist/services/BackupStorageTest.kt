package com.example.myanimelist.services

import com.example.myanimelist.di.servicesModule
import com.example.myanimelist.dto.BackupDTO
import com.example.myanimelist.service.backup.IBackupStorage
import com.example.myanimelist.utilities.getTestingAnime
import com.example.myanimelist.utilities.getTestingReview
import com.example.myanimelist.utilities.getTestingUser
import com.example.myanimelist.utils.Properties
import com.google.gson.Gson
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.koin.core.context.startKoin
import org.koin.test.inject
import org.koin.test.junit5.ClosingKoinTest
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class BackupStorageTest : ClosingKoinTest {
    private val storage: IBackupStorage by inject()
    private val gson: Gson by inject()


    init {
        startKoin {
            modules(servicesModule)
        }

    }

    @BeforeEach
    fun setup() {
        File(Properties.JSON_FILE).delete()
    }

    @Test
    fun save() {
        val dto = BackupDTO(
            arrayListOf(getTestingUser()),
            arrayListOf(getTestingReview()),
            arrayListOf(getTestingAnime()),
        )
        storage.save(dto)

        val savedDto = FileReader(Properties.JSON_FILE).use {
            gson.fromJson(it, BackupDTO::class.java)
        }

        assertAll(
            { assert(dto.animes == savedDto.animes) },
            { assert(dto.users == savedDto.users) },
            { assert(dto.reviews == savedDto.reviews) },
        )

    }

    @Test
    fun load() {
        val dto = BackupDTO(
            arrayListOf(getTestingUser()),
            arrayListOf(getTestingReview()),
            arrayListOf(getTestingAnime())
        )

        FileWriter(Properties.JSON_FILE).use {
            gson.toJson(dto, it)
        }

        val savedDto: BackupDTO? = storage.load()

        assertAll(
            { assert(dto.animes == savedDto?.animes) },
            { assert(dto.users == savedDto?.users) },
            { assert(dto.reviews == savedDto?.reviews) }
        )
    }


}