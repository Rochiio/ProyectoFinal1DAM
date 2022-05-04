package com.example.myanimelist.models

import java.net.URL
import java.sql.Date
import java.util.*

data class User(

    override val id: UUID = UUID.randomUUID(),
    override var name: String,
    override val createDate: Date,
    override var password : String,
    var img: URL?,
    var myList: List<Anime>,

    ): AbstractUser(id, name, createDate, password)
