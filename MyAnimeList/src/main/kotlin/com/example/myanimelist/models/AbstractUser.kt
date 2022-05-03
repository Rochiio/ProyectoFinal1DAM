package com.example.myanimelist.models

import java.net.URL
import java.sql.Date
import java.util.*

abstract class AbstractUser (
    val id: UUID = UUID.randomUUID(),
    var name: String,
    var img: URL?,
    val createDate: Date,
    var password : String,
)
