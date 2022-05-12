package com.example.myanimelist.models

import java.sql.Date
import java.time.LocalDate
import java.util.*

data class Admin(
    override var name: String,
    override var email: String,
    override var password: String,
    override val createDate: LocalDate,
    override val birthDate: LocalDate,
    override val id: UUID = UUID.randomUUID()
) : AbstractUser()
