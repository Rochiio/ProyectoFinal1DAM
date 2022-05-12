package com.example.myanimelist.models

import java.sql.Date
import java.util.*

data class Anime(
    var title: String,
    var titleEnglish: String,
    var types: String,
    var episodes: Int,
    var status: String,
    var date: Date,
    var rating: String,
    var genres: List<String>,
    var img: String?,
    val id: UUID = UUID.randomUUID()

)