package com.example.myanimelist.models

import java.sql.Date
import java.util.*

abstract class AbstractUser {
    abstract val id: UUID
    abstract var name: String
    abstract var email: String
    abstract var password: String
    abstract val createDate: Date
    abstract val birthDate: Date
}
