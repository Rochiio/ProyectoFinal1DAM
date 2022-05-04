package com.example.myanimelist.models

import java.sql.Date
import java.util.*

data class Anime (

    val id: UUID = UUID.randomUUID(),
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
