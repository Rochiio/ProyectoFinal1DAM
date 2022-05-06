package com.example.myanimelist.repositories.animes

import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.Anime
import java.sql.SQLException
import java.util.*

/**
 * @author JoaquinAyG
 * Repository to manage the anime in the database
 */
object AnimeRepository : IAnimeRepository {

    /**
     * Init the data base instance to use
     */
    val db: DataBaseManager = DataBaseManager.getInstance()

    /**
     * @param id responds to the uuid assigned in the database for the object
     * @return the aime with the id if located, null if not
     */
    override fun findById(id: UUID): Anime? {
        val query = "SELECT * FROM animes WHERE id = ?"
        try {
            db.open()
            val result = db.select(query, id.toString()).get()

            if (result.next()) return null


            val anime = Anime(
                id = UUID.fromString(result.getString("id")),
                title = result.getString("title"),
                titleEnglish = result.getString("title_english"),
                types = result.getString("type"),
                episodes = result.getInt("episodes"),
                status = result.getString("status"),
                date = result.getDate("releaseDate"),
                rating = result.getString("rating"),
                genres = result.getString("genre").split(", ").toList(),
                img = result.getString("imageUrl")
            )
            db.close()
            return anime

        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            db.close()
        }
        return null
    }

    /**
     * @return a list with all the anime in the table, the list is empty if no anime was found
     */
    override fun findAll(): List<Anime> {
        val query = "SELECT * FROM animes"
        val animes = ArrayList<Anime>()
        try {
            db.open()
            val result = db.select(query).get()
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
                        genres = result.getString("genre").split(", ").toList(),
                        img = result.getString("imageUrl")
                    )
                )
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            db.close()
        }
        return animes
    }

    /**
     * @param item Is the object Anime to update
     * @return the item after the changes
     */
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
            db.open()
            db.update(
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
            db.close()
        }
        return item
    }

    /**
     * @param item Is the object to add into the table
     * @return The item added to the table
     */
    override fun add(item: Anime): Anime {
        val query = "INSERT INTO animes VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )"
        try {
            db.open()
            db.insert(
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
            db.close()
        }
        return item
    }

    /**
     * @param id the id of the anime to delete in the table
     */
    override fun delete(id: UUID) {
        val query = "DELETE FROM animes WHERE id = ?"
        try {
            db.open()
            db.delete(query, id)
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            db.close()
        }
    }

    /**
     * @param title The title to search on the table
     * @return the anime with the indicated title or null if not found
     */
    override fun findByTitle(title: String): Anime? {
        val query = "SELECT * FROM animes WHERE title = ? OR title_english = ?"
        try {
            db.open()
            val result = db.select(query, title, title).get()

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
                genres = result.getString("genre").split(", ").toList(),
                img = result.getString("imageUrl")
            )
            db.close()
            return anime
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            db.close()
        }
        return null
    }
}