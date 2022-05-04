package com.example.myanimelist.utilities

import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.Genre
import com.example.myanimelist.models.Status
import com.example.myanimelist.models.Type
import java.sql.SQLException
import java.time.LocalDateTime

object DataDB {

    val db = DataBaseManager.getInstance()

    fun insertAnimeTest(){
        val query = "INSERT INTO animes VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )"
        db.open()
        db.insert(query,
            "00000000-0000-0000-0000-000000000000",
            "example",
            "example_english",
            Status.CURRENTLY_AIRING,
            Genre.FANTASY,
            LocalDateTime.now(),
            "/example/example.png",
            "24",
            "PG 12",
            Type.TV )
            .orElseThrow{ SQLException("Error inserting the values") }
        db.close()
    }

    fun deleteAll(){
        val animeQuery = "DELETE FROM animes"
        db.open()
        db.delete(animeQuery)
        db.close()
    }
}