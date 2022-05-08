package com.example.myanimelist.repositories.modelsDB

import com.example.myanimelist.models.User
import java.sql.Date
import java.util.*

internal data class UserDB(
    val id: UUID = UUID.randomUUID(),
    var name: String,
    var email: String,
    var password: String,
    val createDate: Date,
    val birthDate: Date,
    var img: String?
) {
    companion object {
        fun fromDTO(item: User): UserDB =
            UserDB(item.id, item.name, item.email, item.password, item.createDate, item.birthDate, item.img)
    }
}