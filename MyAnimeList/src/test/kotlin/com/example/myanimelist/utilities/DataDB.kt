package com.example.myanimelist.utilities

import com.example.myanimelist.extensions.execute
import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.Admin
import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.User
import com.example.myanimelist.models.enums.Genre
import com.example.myanimelist.models.enums.Status
import com.example.myanimelist.models.enums.Type
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.sql.Date
import java.util.*

object DataDB : KoinComponent {

    private val dataBaseManager by inject<DataBaseManager>()

    fun deleteAll(table: String) {
        val animeQuery = "DELETE FROM $table"
        dataBaseManager.execute {
            dataBaseManager.delete(animeQuery)
        }
    }

    fun getTestingUser() =
        User(
            UUID.randomUUID(),
            "Pepe",
            "asdasd@gmail.com",
            "123",
            Date(Date().time),
            Date(Date().time),
            "img",
            sequenceOf()
        )

    fun getTestingAnime() = Anime(
        id = UUID.randomUUID(),
        title = "example",
        titleEnglish = "example_english",
        status = Status.CURRENTLY_AIRING.value,
        genres = listOf(Genre.FANTASY.value),
        date = Date(Date().time),
        img = "/example/example.png",
        episodes = 24,
        rating = "PG 12",
        types = Type.TV.value
    )

    fun getTestingAdmin(number : Int) =
        Admin(
            UUID.randomUUID(),
            "Pepe$number",
            "asdasd@gmail.com",
            "123",
            Date(Date().time),
            Date(Date().time)
        )

}