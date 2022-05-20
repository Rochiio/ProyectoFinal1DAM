package com.example.myanimelist.utilities

import com.example.myanimelist.extensions.execute
import com.example.myanimelist.managers.DependenciesManager.getDatabaseManager
import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.Review
import com.example.myanimelist.models.User
import com.example.myanimelist.models.enums.Genre
import com.example.myanimelist.models.enums.Status
import com.example.myanimelist.models.enums.Type
import java.time.LocalDate
import java.util.*


private val dataBaseManager = getDatabaseManager()

fun deleteAll(table: String) {
    val animeQuery = "DELETE FROM $table"
    dataBaseManager.execute {
        dataBaseManager.delete(animeQuery)
    }
}

fun getTestingUser() =
    User(
        "pepe",
        "pepe@gmail.com",
        "123",
        LocalDate.parse("17/12/2015"),
        LocalDate.parse("'17/12/2015'"),
        null,
        emptyList(),
        UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454"),
        false
    )

fun getNewTestingUser() =
    User(
        "pepe",
        "pepe@gmail.com",
        "123",
        LocalDate.parse("17/12/2015"),
        LocalDate.parse("'17/12/2015'"),
        null,
        emptyList(),
        UUID.randomUUID(),
        false
    )

fun getTestingAnime() = Anime(
    title = "title",
    titleEnglish = "title",
    status = Status.FINISHED_AIRING.value,
    genres = listOf(Genre.ADVENTURE.value),
    date = LocalDate.parse("17/12/2015"),
    img = "img",
    episodes = 5,
    rating = "rating",
    types = Type.TV.value,
    id = UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455")
)

fun getNewTestingAnime() = Anime(
    title = "title",
    titleEnglish = "title",
    status = Status.FINISHED_AIRING.value,
    genres = listOf(Genre.ADVENTURE.value),
    date = LocalDate.parse("17/12/2015"),
    img = "img",
    episodes = 5,
    rating = "rating",
    types = Type.TV.value,
    id = UUID.randomUUID()
)


fun getTestingReview() = Review(getTestingAnime(), getTestingUser(), 0, "", UUID.randomUUID())
