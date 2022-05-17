package com.example.myanimelist.services

import com.example.myanimelist.dto.BackupDTO
import com.example.myanimelist.managers.DependenciesManager.getBackupStorage
import com.example.myanimelist.managers.DependenciesManager.getGson
import com.example.myanimelist.service.backup.IBackupStorage
import com.example.myanimelist.utilities.DataDB
import com.example.myanimelist.utils.Properties
import com.google.gson.Gson
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class BackupStorageTest {
    private val storage: IBackupStorage = getBackupStorage()
    private val gson: Gson = getGson()


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