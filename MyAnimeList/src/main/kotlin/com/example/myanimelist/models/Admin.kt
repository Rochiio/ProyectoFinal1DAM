package com.example.myanimelist.models

import java.sql.Date
import java.util.*

class Admin (

    id: UUID = UUID.randomUUID(),
    name: String,
    createDate: Date,
    password : String,

    ): AbstractUser(id, name, createDate, password)

