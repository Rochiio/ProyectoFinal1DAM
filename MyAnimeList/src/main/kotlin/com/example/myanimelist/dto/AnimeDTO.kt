package com.example.myanimelist.dto

import com.example.myanimelist.models.Anime
import com.example.myanimelist.service.utils.Utils
import java.time.LocalDate
import java.time.format.DateTimeParseException
import java.util.*

/**
 * @author JoaquinAyG
 */
class AnimeDTO(
    var id: UUID, var title: String, var titleEnglish: String, var types: String, var episodes: Int, var status: String,
    var date: String, var rating: String, var genres: String, var img: String?
) {
    constructor(anime: Anime) : this(
        anime.id, anime.title, anime.titleEnglish, anime.types, anime.episodes,
        anime.status, anime.date.toString(), anime.rating, java.lang.String.join(",", anime.genres), anime.img
    )

    fun fromDTO(): Anime {
        val newDate: LocalDate = try {
            Utils.parseLocalDate(date)
        } catch (e: DateTimeParseException) {
            println("Error de fecha")
            LocalDate.now()
        }
        return Anime(
            title,
            titleEnglish,
            types,
            episodes,
            status,
            newDate,
            rating,
            Arrays.stream(genres.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()).toList(),
            img,
            id
        )
    }
}