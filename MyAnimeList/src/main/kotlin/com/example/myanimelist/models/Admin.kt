package com.example.myanimelist.models

import java.sql.Date
import java.util.*

class Admin(
    override val id: UUID = UUID.randomUUID(),
    override var name: String,
    override val createDate: Date,
    override var password : String,
) : AbstractUser(id, name, createDate, password) {
}