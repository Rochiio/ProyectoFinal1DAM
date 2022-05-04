package com.example.myanimelist.models

import java.sql.Date
import java.util.*

abstract class AbstractUser (
    open val id: UUID = UUID.randomUUID(),
    open var name: String,
    open val createDate: Date,
    open var password : String,
)
