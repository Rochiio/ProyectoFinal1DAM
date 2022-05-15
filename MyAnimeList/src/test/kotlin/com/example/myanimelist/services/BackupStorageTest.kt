package com.example.myanimelist.services

import com.example.myanimelist.adapters.LocalDateTypeAdapter
import com.example.myanimelist.dto.BackupDTO
import com.example.myanimelist.modules.servicesModules
import com.example.myanimelist.service.backup.IBackupStorage
import com.example.myanimelist.utilities.DataDB
import com.example.myanimelist.utils.Properties
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.koin.core.context.startKoin
import org.koin.test.inject
import org.koin.test.junit5.AutoCloseKoinTest
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.time.LocalDate

class BackupStorageTest : AutoCloseKoinTest() {
    private val storage: IBackupStorage by inject()
    private val gson: Gson = GsonBuilder().registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter()).create()


    init {
        startKoin { modules(servicesModules) }
    }

    @BeforeEach
    fun setup() {
        File(Properties.JSON_FILE).delete()
    }

    @Test
    fun save() {
        val dto = BackupDTO(
            arrayListOf(DataDB.getTestingUser()),
            arrayListOf(DataDB.getTestingReview()),
            arrayListOf(DataDB.getTestingAnime()),
            arrayListOf(DataDB.getTestingAdmin())
        )
        storage.save(dto)

        val savedDto = FileReader(Properties.JSON_FILE).use {
            gson.fromJson(it, BackupDTO::class.java)
        }

        assertAll(
            { assert(dto.animes == savedDto.animes) },
            { assert(dto.users == savedDto.users) },
            { assert(dto.admins == savedDto.admins) },
            { assert(dto.reviews == savedDto.reviews) },
        )

    }

    @Test
    fun load() {
        val dto = BackupDTO(
            arrayListOf(DataDB.getTestingUser()),
            arrayListOf(DataDB.getTestingReview()),
            arrayListOf(DataDB.getTestingAnime()),
            arrayListOf(DataDB.getTestingAdmin())
        )

        FileWriter(Properties.JSON_FILE).use {
            gson.toJson(dto, it)
        }

        val savedDto: BackupDTO? = storage.load().orElse(null)

        assertAll(
            { assert(dto.animes == savedDto?.animes) },
            { assert(dto.users == savedDto?.users) },
            { assert(dto.admins == savedDto?.admins) },
            { assert(dto.reviews == savedDto?.reviews) }
        )
    }


}