package com.example.myanimelist.models

import java.sql.Date
import java.util.*

class Admin(
    override val id: UUID = UUID.randomUUID(),
    override var name: String,
    override var email: String,
    override var password: String,
    override val createDate: Date,
    override val birthDate: Date,

    ) : AbstractUser(id, name, email, password, createDate, birthDate)
