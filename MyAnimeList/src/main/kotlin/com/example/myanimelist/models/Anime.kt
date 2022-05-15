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

)