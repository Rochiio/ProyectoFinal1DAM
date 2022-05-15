package com.example.myanimelist.utilities

import com.example.myanimelist.extensions.execute
import com.example.myanimelist.manager.DataBaseManager
import com.example.myanimelist.models.Admin
import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.Review
import com.example.myanimelist.models.User
import com.example.myanimelist.models.enums.Genre
import com.example.myanimelist.models.enums.Status
import com.example.myanimelist.models.enums.Type
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDate
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
            "Pepe",
            "asdasd@gmail.com",
            "123",
            LocalDate.now(),
            LocalDate.now(),
            "img",
            emptyList(),
            UUID.randomUUID()
        )

    fun getTestingAnime() = Anime(
        title = "example",
        titleEnglish = "example_english",
        status = Status.CURRENTLY_AIRING.value,
        genres = listOf(Genre.FANTASY.value),
        date = LocalDate.now(),
        img = "/example/example.png",
        episodes = 24,
        rating = "PG 12",
        types = Type.TV.value
    )

    fun getTestingAdmin(number: Int = 0) =
        Admin(
            "Pepe$number",
            "asdasd@gmail.com",
            "123",
            LocalDate.now(),
            LocalDate.now()
        )

    fun getTestingReview() = Review(getTestingAnime(), getTestingUser(), 0, "", UUID.randomUUID())

}