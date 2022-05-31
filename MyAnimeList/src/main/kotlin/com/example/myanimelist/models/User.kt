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
    val myList: MutableList<Anime>,
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
    ) : this(name, email, password, createDate, birthDate, img, emptyList<Anime>().toMutableList(), id, admin)

    constructor(
        name: String,
        email: String,
        password: String,
        createDate: LocalDate,
        birthDate: LocalDate,
        img: String?,
        id: UUID = UUID.randomUUID(),
    ) : this(name, email, password, createDate, birthDate, img, emptyList<Anime>().toMutableList(), id, false)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }


}
