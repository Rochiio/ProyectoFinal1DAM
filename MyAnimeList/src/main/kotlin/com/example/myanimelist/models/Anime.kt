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

    )