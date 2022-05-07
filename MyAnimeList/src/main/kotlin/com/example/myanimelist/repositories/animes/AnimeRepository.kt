package com.example.myanimelist.repositories.animes

import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.Anime
import java.sql.SQLException
import java.util.*

class AnimeRepository(private val databaseManager: DataBaseManager) : IAnimeRepository {

    override fun findById(id: UUID): Anime? {
        val query = "SELECT * FROM animes WHERE id = ?"
        try {
            databaseManager.open()
            val result = databaseManager.select(query, id.toString()).get()

            if (!result.next()) return null

            val anime = Anime(
                id = UUID.fromString(result.getString("id")),
                title = result.getString("title"),
                titleEnglish = result.getString("title_english"),
                types = result.getString("type"),
                episodes = result.getInt("episodes"),
                status = result.getString("status"),
                date = result.getDate("releaseDate"),
                rating = result.getString("rating"),
                genres = result.getString("genre").split(",").toList(),
                img = result.getString("imageUrl")
            )
            databaseManager.close()
            return anime

        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            databaseManager.close()
        }
        return null
    }

    override fun findAll(): List<Anime> {
        val query = "SELECT * FROM animes"
        val animes = ArrayList<Anime>()
        try {
            databaseManager.open()
            val result = databaseManager.select(query).get()
            while (result.next()) {
                animes.add(
                    Anime(
                        id = UUID.fromString(result.getString("id")),
                        title = result.getString("title"),
                        titleEnglish = result.getString("title_english"),
                        types = result.getString("type"),
                        episodes = result.getInt("episodes"),
                        status = result.getString("status"),
                        date = result.getDate("releaseDate"),
                        rating = result.getString("rating"),
                        genres = result.getString("genre").split(",").toList(),
                        img = result.getString("imageUrl")
                    )
                )
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            databaseManager.close()
        }
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
        try {
            databaseManager.open()
            databaseManager.update(
                query,
                item.title,
                item.titleEnglish,
                item.status,
                item.genres.joinToString(separator = ", "),
                item.date,
                item.img,
                item.episodes,
                item.rating,
                item.types,
                item.id.toString()
            )
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            databaseManager.close()
        }
        return item
    }

    override fun add(item: Anime): Anime {
        val query = "INSERT INTO animes VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )"
        try {
            databaseManager.open()
            databaseManager.insert(
                query,
                item.id,
                item.title,
                item.titleEnglish,
                item.status,
                item.genres.joinToString(separator = ", "),
                item.date,
                item.img,
                item.episodes,
                item.rating,
                item.types
            )
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            databaseManager.close()
        }
        return item
    }

    override fun delete(id: UUID) {
        val query = "DELETE FROM animes WHERE id = ?"
        try {
            databaseManager.open()
            databaseManager.delete(query, id)
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            databaseManager.close()
        }
    }
}