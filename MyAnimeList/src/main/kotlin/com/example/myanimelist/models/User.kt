package com.example.myanimelist.models

import java.time.LocalDate
import java.util.*

data class User(
     var name: String,
     var email: String,
     var password: String,
     val createDate: LocalDate,
     val birthDate: LocalDate,
     var img: String?,
     val myList: List<Anime>,
     val id: UUID = UUID.randomUUID(),
     val admin: Boolean
) {

    constructor(
        name: String,
        email: String,
        password: String,
        createDate: LocalDate,
        birthDate: LocalDate,
        img: String?,
        admin: Boolean,
        id: UUID = UUID.randomUUID()
    ) : this(name, email, password, createDate, birthDate, img, emptyList<Anime>(), id, admin)

    constructor(
        name: String,
        email: String,
        password: String,
        createDate: LocalDate,
        birthDate: LocalDate,
        img: String?,
        id: UUID = UUID.randomUUID(),
        ) : this(name, email, password, createDate, birthDate, img, emptyList<Anime>(), id, false)


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (name != other.name) return false
        if (email != other.email) return false
        if (password != other.password) return false
        if (createDate != other.createDate) return false
        if (birthDate != other.birthDate) return false
        if (img != other.img) return false
        if (myList != other.myList) return false
        if (id != other.id) return false
        if (admin != other.admin) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + createDate.hashCode()
        result = 31 * result + birthDate.hashCode()
        result = 31 * result + (img?.hashCode() ?: 0)
        result = 31 * result + myList.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + admin.hashCode()
        return result
    }


}
