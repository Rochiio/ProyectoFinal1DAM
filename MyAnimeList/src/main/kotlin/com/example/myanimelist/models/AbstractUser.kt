package com.example.myanimelist.models

import java.sql.Date
import java.util.*

abstract class AbstractUser (
    open val id: UUID = UUID.randomUUID(),
    open var name: String,
    open var email: String,
    open var password : String,
    open val createDate: Date,
    open val birthDate: Date,

)
