package com.example.myanimelist.models

import java.net.URL
import java.sql.Date

data class Anime (

    val id: Int,
    var title: String,
    var titleEnglish: String,
    var types: Type,
    var episodes: Int,
    var status: Status,
    var date: Date,
    var rating: String,
    var duration: String,
    var genres: List<Genre>,

    var img: URL? = null,

    )
