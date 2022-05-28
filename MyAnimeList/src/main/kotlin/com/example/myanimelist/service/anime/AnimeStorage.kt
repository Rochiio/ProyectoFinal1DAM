package com.example.myanimelist.service.anime

import com.example.myanimelist.dto.AnimeDTO
import com.example.myanimelist.utils.Properties
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.util.*

/**
 * @author JoaquinAyG
 */
class AnimeStorage : IAnimeStorage {
    init {
        mkdir()
    }

    override fun mkdir() {
        val dir = Path.of(Properties.CSV_DIR)
        if (Files.exists(dir)) return
        try {
            Files.createDirectory(dir)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun save(dtoList: List<AnimeDTO>) {
        val header =
            "id,title,titleEnglish,types,episodes,status,date,rating,genres,img".replace(',', Properties.CSV_SEPARATOR)
        val csv = StringBuilder(header)
        val csvList = dtoList.stream().map { animeDTO: AnimeDTO -> toCSV(animeDTO) }.toList()
        for (s in csvList) {
            csv.append("\n")
            csv.append(s)
        }
        try {
            FileWriter(Properties.ANIME_SAVE).use { writer -> writer.write(csv.toString()) }
        } catch (e: Exception) {
            println("Error writing the file")
        }
    }

    override fun load(): List<AnimeDTO> {
        try {
            return Files.lines(Path.of(Properties.ANIME_CSV)).skip(1).map {
                parse(it)
            }.toList()
        } catch (e: Exception) {
            println("Error reading the file")
        }
        return emptyList()
    }

    private fun parse(line: String): AnimeDTO {
        val fields = line.split(Properties.CSV_SEPARATOR.toString().toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        val id = UUID.fromString(fields[0])
        val title = fields[1]
        val titleEnglish = fields[2]
        val types = fields[3]
        val episodes = fields[4].toDouble().toInt()
        val status = fields[5]
        val rating = fields[6]
        val genres = fields[8]
        val date = fields[9]
        return AnimeDTO(id, title, titleEnglish, types, episodes, status, date, rating, genres, "$id.jpg")
    }

    private fun toCSV(animeDTO: AnimeDTO): String {
        return """
            ${animeDTO.id}${Properties.CSV_SEPARATOR}${animeDTO.title}${Properties.CSV_SEPARATOR}${animeDTO.titleEnglish}${Properties.CSV_SEPARATOR}${animeDTO.types}${Properties.CSV_SEPARATOR}${animeDTO.episodes}${Properties.CSV_SEPARATOR}${animeDTO.status}${Properties.CSV_SEPARATOR}${animeDTO.date}${Properties.CSV_SEPARATOR}${animeDTO.rating}${Properties.CSV_SEPARATOR}${animeDTO.genres}${Properties.CSV_SEPARATOR}${animeDTO.img}
            """.trimIndent()
    }
}