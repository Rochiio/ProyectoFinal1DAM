package com.example.myanimelist.service.backup

import com.example.myanimelist.dto.BackupDTO
import com.example.myanimelist.utils.Properties
import com.google.gson.Gson
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path

/**
 * @author JoaquinAyG
 */
class BackupStorage(gson: Gson) : IBackupStorage {
    private val gson: Gson

    init {
        mkdir()
        this.gson = gson
    }

    override fun mkdir() {
        val dir = Path.of(Properties.JSON_DIR)
        if (Files.exists(dir)) return
        try {
            Files.createDirectory(dir)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun save(dtoList: BackupDTO) {
        try {
            FileWriter(Properties.JSON_FILE).use { writer -> gson.toJson(dtoList, writer) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun load(): BackupDTO? {
        try {
            FileReader(Properties.JSON_FILE).use {
                return gson.fromJson(
                    it,
                    BackupDTO::class.java
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}