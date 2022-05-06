package com.example.myanimelist.repositories.animes

import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.Genre
import com.example.myanimelist.models.Status
import com.example.myanimelist.models.Type
import com.example.myanimelist.utilities.DataDB
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.sql.Date
import java.util.*

internal class AnimeRepositoryTest {

    private val repo = AnimeRepository
  
    private val animeGiven = Anime(
        id = UUID.fromString("00000000-0000-0000-0000-000000000000"),

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

    @BeforeEach
    internal fun setUp() {
        DataDB.deleteAll()
        DataDB.insertAnimeTest()
    }

    @Test
    fun findById() {
        val animetest = repo.findById(animeGiven.id)
        assertAll(
            { assertTrue(animetest != null) },
            { Assertions.assertEquals(animetest!!.id, animeGiven.id) },
            { Assertions.assertEquals(animetest!!.title, animeGiven.title) },
            { Assertions.assertEquals(animetest.toString(), animeGiven.toString()) }
        )
    }

    @Test
    fun findByIdNotPresent() {
        val animetest = repo.findById(UUID.fromString("10000000-0000-0000-0000-000000000000"))
        assertAll(
            { assertTrue(animetest == null) }
        )

    }

    @Test
    fun findAll() {
        val animeListTest = repo.findAll()
        assertAll(
            { assertTrue(animeListTest.isNotEmpty()) },
            { Assertions.assertEquals(animeListTest.size, 1) },
            { assertTrue(animeListTest.map { it.id }.contains(animeGiven.id)) },
        )
    }

    @Test
    fun update() {
        animeGiven.title = "new title"
        animeGiven.titleEnglish = "new title"
        animeGiven.status = Status.NOT_YET_AIRED.value
        animeGiven.genres = listOf(Genre.ACTION.value)
        animeGiven.date = Date.valueOf("1950-01-02")
        animeGiven.img = "new directory"
        animeGiven.episodes = 64
        animeGiven.rating = "new rating"
        animeGiven.types = Type.OVA.value

        val response = repo.update(animeGiven)

        val animeRecived = repo.findById(animeGiven.id)
        assertAll(
            { assertTrue(animeRecived != null) },
            { Assertions.assertEquals(animeRecived!!.id, animeGiven.id) },
            { Assertions.assertEquals(animeRecived!!.title, animeGiven.title) },
            { Assertions.assertEquals(animeRecived.toString(), animeGiven.toString()) },
            { Assertions.assertEquals(response.id, animeGiven.id) },
            { Assertions.assertEquals(response.title, animeGiven.title) },
            { Assertions.assertEquals(response.toString(), animeGiven.toString()) }
        )
    }

    @Test
    fun add() {
        val newAnime = Anime(
            title = "new title",
            titleEnglish = "new title",
            status = Status.NOT_YET_AIRED.value,
            genres = listOf(Genre.ACTION.value),
            date = Date(Date().time),
            img = "new directory",
            episodes = 64,
            rating = "new rating",
            types = Type.OVA.value,
        )

        val response = repo.add(newAnime)

        val animeRecived = repo.findById(newAnime.id)
        assertAll(
            { assertTrue(animeRecived != null) },
            { Assertions.assertEquals(animeRecived!!.id, newAnime.id) },
            { Assertions.assertEquals(animeRecived!!.title, newAnime.title) },
            { Assertions.assertEquals(animeRecived.toString(), newAnime.toString()) },
            { Assertions.assertEquals(response.id, newAnime.id) },
            { Assertions.assertEquals(response.title, newAnime.title) },
            { Assertions.assertEquals(response.toString(), newAnime.toString()) }
        )
    }

    @Test
    fun delete() {

        repo.delete(animeGiven.id)
        val animeRecived = repo.findById(animeGiven.id)
        assertAll(
            { assertTrue(animeRecived == null) },
        )
    }

    @Test
    fun getDb() {
        assertAll(
            { assertTrue(repo.db == DataBaseManager.getInstance()) }
        )
    }
}