package com.example.myanimelist.models

import java.sql.Date
import java.util.*

data class Anime(

    var id: UUID = UUID.randomUUID(),
    var title: String,
    var titleEnglish: String,
    var types: String,
    var episodes: Int,
    var status: String,
    var date: Date,
    var rating: String,
    var genres: List<String>,

    var img: String? = null,

    ) {
    data class AnimeBuilder(
        val id: UUID = UUID.randomUUID(),
        val title: String = "",
        val titleEnglish: String = "",
        val types: String = Type.MUSIC.value,
        val episodes: Int = 0,
        val status: String = Status.NOT_YET_AIRED.value,
        val date: Date = Date(Date().time),
        val rating: String = "",
        val genres: List<String> = listOf(),
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