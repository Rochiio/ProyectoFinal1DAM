package com.example.myanimelist.service.txt

import com.example.myanimelist.dto.LoadDTO
import com.example.myanimelist.utils.Properties
import com.example.myanimelist.utils.Themes
import com.google.gson.Gson
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path

class TxtBackup : ITxtStorage, KoinComponent {
    private val gson: Gson by inject()

    init {
        mkdir()
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

    override fun save(dtoList: LoadDTO) {
        try {
            FileWriter(Properties.LOAD_FILE).use { writer -> gson.toJson(dtoList, writer) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun load(): LoadDTO? {
        try {
            FileReader(Properties.LOAD_FILE).use {
                return gson.fromJson(
                    it,
                    LoadDTO::class.java

                )
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return null
    }

    companion object {
        fun changeNightMode(theme: Themes) {
            val path = Properties.LOAD_FILE
            var content = ""
            try {
                content = Files.readString(Path.of(path))
            } catch (e: IOException) {
                println("Error file cant be opened")
            }
            if (theme === Themes.OSCURO) {
                content = content.replace("\"nightMode\": false", "\"nightMode\": true")
            } else if (theme === Themes.CLARO) {
                content = content.replace("\"nightMode\": true", "\"nightMode\": false")
            }
            try {
                FileWriter(path).use { fw -> fw.write(content) }
            } catch (e: IOException) {
                println("Error file cant be edited")
            }
        }
    }
}