package com.example.myanimelist.models

import java.sql.Date
import java.util.*

data class User(
    override var name: String,
    override var email: String,
    override var password: String,
    override val createDate: Date,
    override val birthDate: Date,
    var img: String?,
    val myList: List<Anime>,
    override val id: UUID = UUID.randomUUID()
) : AbstractUser() {

    constructor(
        name: String,
        email: String,
        password: String,
        createDate: Date,
        birthDate: Date,
        img: String?,
        id: UUID = UUID.randomUUID()
    ) :
            this(name, email, password, createDate, birthDate, img, emptyList<Anime>(), id)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (name != other.name) return false
        if (email != other.email) return false
        if (password != other.password) return false
        if (createDate != other.createDate) return false
        if (birthDate != other.birthDate) return false
        if (img != other.img) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + createDate.hashCode()
        result = 31 * result + birthDate.hashCode()
        result = 31 * result + (img?.hashCode() ?: 0)
        return result
    }
}
