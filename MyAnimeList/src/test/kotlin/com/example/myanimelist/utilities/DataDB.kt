package com.example.myanimelist.utilities

import com.example.myanimelist.di.isTesting
import com.example.myanimelist.extensions.execute
import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.Review
import com.example.myanimelist.models.User
import com.example.myanimelist.models.enums.Genre
import com.example.myanimelist.models.enums.Status
import com.example.myanimelist.models.enums.Type
import com.example.myanimelist.utils.Properties
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

private val datetimeFormatter = DateTimeFormatter.ISO_DATE

/**
 * Returns an object that is already in the database
 */
fun getTestingUser() =
    User(
        "pepe",
        "pepe@gmail.com",
        "123",
        LocalDate.parse("2015-12-17", datetimeFormatter),
        LocalDate.parse("2015-12-17", datetimeFormatter),
        null,
        emptyList<Anime>().toMutableList(),
        UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454"),
        false
    )

fun getTestingUserDelete() =
    User(
        "pepedelete",
        "pepe@gmail.com",
        "123",
        LocalDate.parse("2015-12-17", datetimeFormatter),
        LocalDate.parse("2015-12-17", datetimeFormatter),
        null,
        emptyList<Anime>().toMutableList(),
        UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3444"),
        false
    )

fun getTestingUserUpdate() =
    User(
        "pepeupdate",
        "pepe@gmail.com",
        "123",
        LocalDate.parse("2015-12-17", datetimeFormatter),
        LocalDate.parse("2015-12-17", datetimeFormatter),
        null,
        emptyList<Anime>().toMutableList(),
        UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3434"),
        false
    )

/**
 * Returns an object that is not in the database
 */
fun getNewTestingUser() =
    User(
        //Unique name
        UUID.randomUUID().toString(),
        "pepe@gmail.com",
        "123",
        LocalDate.parse("2015-12-17", datetimeFormatter),
        LocalDate.parse("2015-12-17", datetimeFormatter),
        null,
        emptyList<Anime>().toMutableList(),
        UUID.randomUUID(),
        false
    )

fun getTestingAnime() = Anime(
    title = "title",
    titleEnglish = "title",
    status = Status.FINISHED_AIRING.value,
    genres = listOf(Genre.ADVENTURE.value),
    date = LocalDate.parse("2015-12-17", datetimeFormatter),
    img = "img",
    episodes = 5,
    rating = "rating",
    types = Type.TV.value,
    id = UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455")
)

fun getTestingAnimeDelete() = Anime(
    title = "titledelete",
    titleEnglish = "title",
    status = Status.FINISHED_AIRING.value,
    genres = listOf(Genre.ADVENTURE.value),
    date = LocalDate.parse("2015-12-17", datetimeFormatter),
    img = "img",
    episodes = 5,
    rating = "rating",
    types = Type.TV.value,
    id = UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3457")
)

fun getTestingAnimeUpdate() = Anime(
    title = "titleUpdate",
    titleEnglish = "title",
    status = Status.FINISHED_AIRING.value,
    genres = listOf(Genre.ADVENTURE.value),
    date = LocalDate.parse("2015-12-17", datetimeFormatter),
    img = "img",
    episodes = 5,
    rating = "rating",
    types = Type.TV.value,
    id = UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3456")
)

fun getNewTestingAnime() = Anime(
    title = "title",
    titleEnglish = "title",
    status = Status.FINISHED_AIRING.value,
    genres = listOf(Genre.ADVENTURE.value),
    date = LocalDate.parse("2015-12-17", datetimeFormatter),
    img = "img",
    episodes = 5,
    rating = "rating",
    types = Type.TV.value,
    id = UUID.randomUUID()
)

fun getTestingReview() =
    Review(getTestingAnime(), getTestingUser(), 10, "ta guapo", UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3456"))

fun getNewTestingReview() =
    Review(getTestingAnimeUpdate(), getTestingUserUpdate(), 10, "ta guapo", UUID.randomUUID())


fun resetDb(db: DataBaseManager) {
    isTesting = true
    db.execute {
        initData(Properties.SCRIPT_FILE_DATABASE, false)
    }
}