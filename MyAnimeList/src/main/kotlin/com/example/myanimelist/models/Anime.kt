package com.example.myanimelist.models

import java.time.LocalDate
import java.util.*

data class Anime(
    var title: String,
    var titleEnglish: String,
    var types: String,
    var episodes: Int,
    var status: String,
    var date: LocalDate,
    var rating: String,
    var genres: List<String>,
    var img: String?,
    val id: UUID = UUID.randomUUID()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Anime

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

