package com.example.myanimelist.service.utils

import com.example.myanimelist.extensions.getLogger
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.time.LocalDate
import java.util.*

object Utils {
    var logger = getLogger<Utils>()
    fun getFileExtension(filename: String): Optional<String> {
        return Optional.ofNullable(filename)
            .filter { f: String -> f.contains(".") }
            .map { f: String -> f.substring(filename.lastIndexOf(".") + 1) }
    }

    fun cp(from: String, to: String): Boolean {
        logger.info("Copy files from $from to $to")
        val initial = Path.of(from)
        if (!Files.exists(initial)) {
            logger.warn("Non existing file")
            return false
        }
        try {
            Files.copy(initial, Paths.get(to), StandardCopyOption.REPLACE_EXISTING)
        } catch (e: IOException) {
            logger.warn("Error copying")
            println()
            return false
        }
        return true
    }

    fun parseLocalDate(date: String?): LocalDate {
        val fecha = date!!.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return LocalDate.of(fecha[2].toInt(), fecha[1].toInt(), fecha[0].toInt())
    }

    fun deleteFile(file: String): Boolean {
        logger.info("Deleting $file")
        val path = Path.of(file)
        if (!Files.exists(path)) {
            logger.warn("File not found")
            return false
        }
        try {
            Files.delete(path)
        } catch (e: IOException) {
            logger.warn("Error deleting")
            return false
        }
        return true
    }
}