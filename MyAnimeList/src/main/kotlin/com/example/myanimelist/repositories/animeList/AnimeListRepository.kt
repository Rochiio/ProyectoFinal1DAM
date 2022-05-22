package com.example.myanimelist.repositories.animeList

import com.example.myanimelist.extensions.execute
import com.example.myanimelist.manager.DataBaseManager
import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.User
import com.example.myanimelist.repositories.users.IUsersRepository
import org.apache.logging.log4j.Logger

class AnimeListRepository(
    private var dataBaseManager: DataBaseManager,
    private val logger: Logger,
    private val usersRepository: IUsersRepository
) : IRepositoryAnimeList {


    /**
     * Añade un anime a la lista de un usuario
     * @param item Anime
     * @return Anime?
     */
    override fun add(item: Anime, user: User): Anime? {
        val query = "INSERT INTO animeLists VALUES(?, ?)"
        dataBaseManager.execute(logger) {
            dataBaseManager.insert(
                query,
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
    override fun delete(item: Anime, user: User): Anime? {
        val query = "DELETE FROM animeLists where idAnime= ? and idUser= ?"
        dataBaseManager.execute {
            dataBaseManager.delete(query, item.id, user.id)
            return item
        }
        return null
    }

    override fun findByUserId(user: User): List<Anime> = usersRepository.getAnimeLists(user.id)
}