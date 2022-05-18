package com.example.myanimelist.models

import java.time.LocalDate
import java.util.*

abstract class AbstractUser {
    abstract val id: UUID
    abstract var name: String
    abstract var email: String
    abstract var password: String
    abstract val createDate: LocalDate
    abstract val birthDate: LocalDate
    abstract val admin: Boolean
}
