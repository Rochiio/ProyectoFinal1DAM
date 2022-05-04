package com.example.myanimelist.repositories

import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.Genre
import com.example.myanimelist.models.Status
import com.example.myanimelist.models.Type
import java.util.*
import kotlin.collections.ArrayList

class AnimeRepository (val db: DataBaseManager): IAnimeRepository {

    /**
     * Busca un pais por su nombre
     *
     * @param nombre nombre del pais
     * @return el pais encontrado o null si no lo encuentra
     */

    override fun findbyId(id: UUID): Anime? {
        val query = "SELECT * FROM animes WHERE id = ?"
        db.open()
        val result = db.select(query, id).get()
        if (result.first()) {
             val anime = Anime(
                 UUID.fromString(result.getString("id")),
                 result.getString("title"),
                 result.getString("title_english"),
                 Type.valueOf(result.getString("type")),
                 result.getInt("episodes"),
                 Status.valueOf(result.getString("status")),
                 result.getDate("releaseDate"),
                 result.getString("rating"),
                 result.getString("genre").split(",").map { Genre.valueOf(it)},
                 result.getString("imageUrl")

            );
            db.close();
            return anime
        }
        return null
    }

    override fun findAll(): List<Anime?> {
        val query = "SELECT * FROM animes"
        db.open()
        val result = db.select(query).get()
        val animes = ArrayList<Anime>()
        while (result.next()) {
            animes.add(Anime(
                UUID.fromString(result.getString("id")),
                result.getString("title"),
                result.getString("title_english"),
                Type.valueOf(result.getString("type")),
                result.getInt("episodes"),
                Status.valueOf(result.getString("status")),
                result.getDate("releaseDate"),
                result.getString("rating"),
                result.getString("genre").split(",").map { Genre.valueOf(it) },
                result.getString("imageUrl")
            ))

        }
        db.close();
        return animes
    }

    override fun update(item: Anime?): Anime? {
        val query = "UPDATE anime SET " +
                "title = ?," +
                "title_english = ?," +
                "status = ?," +
                "genre = ?," +
                "releaseDate = ?," +
                "imageUrl = ?," +
                "episodes = ?," +
                "rating = ?," +
                "type = ?," +
                "WHERE id = ?"
        db.open()
        db.update(query, item!!.title, item.titleEnglish, item.status, item.genres, item.date, item.img, item.episodes, item.rating, item.types, item.id )
        db.close();
        return item
    }

    override fun add(item: Anime?): Anime? {
        TODO("Not yet implemented")
    }

    override fun delete(): Anime? {
        TODO("Not yet implemented")
    }

    override fun findByName(name: String): Anime? {
        TODO("Not yet implemented")
    }
}