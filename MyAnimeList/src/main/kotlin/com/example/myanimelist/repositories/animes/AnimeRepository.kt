package com.example.myanimelist.repositories.animes

import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.*
import java.sql.Date
import java.sql.SQLException
import java.util.*
import kotlin.collections.ArrayList

object AnimeRepository : IAnimeRepository {

    val db : DataBaseManager = DataBaseManager.getInstance()
    override fun findById(id: UUID): Anime? {
        val query = "SELECT * FROM animes WHERE id = ?"
        db.open()
        val result = db.select(query, id.toString()).get()
        if (result.next()) {
            val anime = Anime.AnimeBuilder(
                id = UUID.fromString(result.getString("id")),
                title = result.getString("title"),
                titleEnglish = result.getString("title_english"),
                types = Type.valueOf(result.getString("type")),
                episodes = result.getInt("episodes"),
                status = Status.valueOf(result.getString("status")),
                date = result.getDate("releaseDate"),
                rating = result.getString("rating"),
                genres = result.getString("genre").split(", ").map { Genre.valueOf(it) }.toList(),
                img = result.getString("imageUrl")
            ).build()
            db.close()
            return anime
        }
        db.close()
        return null
    }

    override fun findAll(): List<Anime> {
        val query = "SELECT * FROM animes"
        db.open()
        val result = db.select(query).get()
        val animes = ArrayList<Anime>()
        while (result.next()) {
            animes.add(
                Anime(
                    id = UUID.fromString(result.getString("id")),
                    title = result.getString("title"),
                    titleEnglish = result.getString("title_english"),
                    types = Type.valueOf(result.getString("type")),
                    episodes = result.getInt("episodes"),
                    status = Status.valueOf(result.getString("status")),
                    date = result.getDate("releaseDate"),
                    rating = result.getString("rating"),
                    genres = result.getString("genre").split(",").map { Genre.valueOf(it) },
                    img = result.getString("imageUrl")
                )
            )
        }
        db.close()
        return animes
    }

    override fun update(item: Anime): Anime {
        val query = "UPDATE animes SET " +
                "title = ?," +
                "title_english = ?," +
                "status = ?," +
                "genre = ?," +
                "releaseDate = ?," +
                "imageUrl = ?," +
                "episodes = ?," +
                "rating = ?," +
                "type = ?" +
                "WHERE id = ?"
        db.open()
        db.update(query, item.title, item.titleEnglish, item.status.toString(), item.genres.joinToString(separator = ", "), item.date, item.img, item.episodes, item.rating, item.types.toString(), item.id.toString() )
        db.close()
        return item
    }

    override fun add(item: Anime): Anime {
        val query = "INSERT INTO animes VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )"
        db.open()
        db.insert(query, item.id, item.title, item.titleEnglish, item.status, item.genres.joinToString(separator = ", "), item.date, item.img, item.episodes, item.rating, item.types )
        db.close()
        return item
    }

    override fun delete(id: UUID) {
        val query = "DELETE FROM animes WHERE id = ?"
        db.open()
        db.delete(query, id)
        db.close()
    }

    override fun findByTitle(title: String): Anime? {
        val query = "SELECT * FROM animes WHERE title = ? OR title_english = ?"
        db.open()
        val result = db.select(query, title, title).get()
        if (result.next()) {
            val anime = Anime.AnimeBuilder(
                id = UUID.fromString(result.getString("id")),
                title = result.getString("title"),
                titleEnglish = result.getString("title_english"),
                types = Type.valueOf(result.getString("type")),
                episodes = result.getInt("episodes"),
                status = Status.valueOf(result.getString("status")),
                date = result.getDate("releaseDate"),
                rating = result.getString("rating"),
                genres = result.getString("genre").split(",").map { Genre.valueOf(it) },
                img = result.getString("imageUrl")
            ).build()
            db.close()
            return anime
        }
        db.close()
        return null
    }
}