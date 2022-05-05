package com.example.myanimelist.models

import java.sql.Date
import java.time.LocalDateTime
import java.util.*

data class Anime (

    var id: UUID = UUID.randomUUID(),
    var title: String,
    var titleEnglish: String,
    var types: Type,
    var episodes: Int,
    var status: Status,
    var date: Date,
    var rating: String,
    var genres: List<Genre>,

    var img: String? = null,

    )
{
    data class AnimeBuilder(
        val id: UUID = UUID.randomUUID(),
        val title: String = "",
        val titleEnglish: String = "",
        val types: Type = Type.MUSIC,
        val episodes: Int = 0,
        val status: Status = Status.NOT_YET_AIRED,
        val date: Date = Date.valueOf("2022-05-05T09:24:26.863093677"),
        val rating: String = "",
        val genres: List<Genre> = listOf(),
        val img: String? = ""
    ) {
        fun build(): Anime {
            return Anime(
                id = id,
                title = title,
                titleEnglish = titleEnglish,
                types = types,
                episodes = episodes,
                status = status,
                date = date,
                rating = rating,
                genres = genres,
                img = img
            )
        }
    }
}