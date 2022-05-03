package com.example.myanimelist.models

import java.net.URL
import java.sql.Date

data class User(

    val id: Int,
    var name: String,
    var img: URL?,
    val createDate: Date,
    var password : String,
    var myList: List<Anime>,

)
