package com.example.myanimelist.repositories.animeList

import com.example.myanimelist.extensions.execute
import com.example.myanimelist.manager.DataBaseManager
import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.User
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class AnimeListRepository(
    var dbManager: DataBaseManager,
    var user: User,
) : ICRUDAnimeList<Anime, User> {
    val logger: Logger = LogManager.getLogger(AnimeListRepository::class)

    /**
     * Añade un anime a la lista de un usuario
     * @param item Anime
     * @return Anime?
     */
    override fun add(item: Anime) : Anime? {
        val query = "INSERT INTO animeLists VALUES(?, ?)"
        dbManager.execute(logger) {
            dbManager.insert(query,
                user.id,
                item.id
            )
            return item
        }
        return null
    }

    /**
     * Elimina un anime de la lista de un usuario
     * @param item Anime
     * @return Anime?
     */
    override fun delete(item: Anime) : Anime? {
    val query = "DELETE FROM animeLists where id=?"
        dbManager.execute {
            dbManager.delete(query, item.id)
            return item
        }
        return null
    }
}