package com.example.myanimelist.repositories.animeList

import com.example.myanimelist.extensions.execute
import com.example.myanimelist.manager.DataBaseManager
import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.User
import com.example.myanimelist.repositories.modelsDB.UserDB
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.*

class AnimeListRepository(
    var dataBaseManager: DataBaseManager,
    val logger: Logger
) : IRepositoryAnimeList {


    /**
     * Añade un anime a la lista de un usuario
     * @param item Anime
     * @return Anime?
     */
    override fun add(item: Anime, user : User) : Anime? {
        val query = "INSERT INTO animeLists VALUES(?, ?)"
        dataBaseManager.execute(logger) {
            dataBaseManager.insert(query,
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
    override fun delete(item: Anime, user : User) : Anime? {
    val query = "DELETE FROM animeLists where idAnime= ? and idUser= ?"
        dataBaseManager.execute {
            dataBaseManager.delete(query, item.id, user.id)
            return item
        }
        return null
    }

    override fun findByUserId(user: User): List<UUID>? {
        val query = "SELECT * FROM animeLists where idUser = ?"
        dataBaseManager.execute {
            val set = dataBaseManager.select(query, user.id)
            val list :  MutableList<UUID> = mutableListOf()
            while (set.next()) {
                val id = UUID.fromString(set.getString("idAnime"))

                list.add(id)
                logger.info("Se han encontrado los animes: $list")
            }
            return list
        }
        return null
    }
}