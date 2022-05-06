package com.example.myanimelist.models

import java.sql.Date
import java.util.*

data class User(

    override val id: UUID = UUID.randomUUID(),
    override var name: String,
    override var email: String,
    override var password: String,
    override val createDate: Date,
    override val birthDate: Date,
    var img: String?,
    var myList: List<Anime>?,

    ) : AbstractUser(id, name, email, password, createDate, birthDate)
