package com.example.myanimelist.repositories.users

import com.example.myanimelist.extensions.execute
import com.example.myanimelist.manager.DataBaseManager
import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.User
import com.example.myanimelist.repositories.modelsDB.UserDB
import org.apache.logging.log4j.Logger
import java.util.*


class UsersRepository(
    private val databaseManager: DataBaseManager,
    val logger: Logger
) : IUsersRepository {
    override fun findByName(name: String): List<User> {
        val list = mutableListOf<UserDB>()
        databaseManager.execute(logger) {
            val set = databaseManager.select("SELECT * FROM usuarios WHERE nombre LIKE ?", "%$name%")

            while (set.next()) {
                val id = UUID.fromString(set.getString("id"))
                val user = UserDB(
                    id,
                    set.getString("nombre"),
                    set.getString("email"),
                    set.getString("password"),
                    set.getDate("date_alta").toLocalDate(),
                    set.getDate("date_nacimiento").toLocalDate(),
                    set.getString("imageUrl"),
                    set.getBoolean("admin")
                )
                logger.info("[findByName] Encotrado usuario $user")
                list.add(user)
            }
        }
        return list.map { mapToModel(it) ?: return emptyList() }
    }

    override fun findById(id: UUID): User? {
        var returnItem: UserDB? = null

        databaseManager.execute(logger) {
            val set = databaseManager.select("SELECT * FROM usuarios WHERE id = ?", id.toString())

            if (!set.next()) return null

            returnItem = UserDB(
                UUID.fromString(set.getString("id")),
                set.getString("nombre"),
                set.getString("email"),
                set.getString("password"),
                set.getDate("date_alta").toLocalDate(),
                set.getDate("date_nacimiento").toLocalDate(),
                set.getString("imageUrl"),
                set.getBoolean("admin")
            )
            logger.info("Encotrado usuario $returnItem")
        }
        return mapToModel(returnItem)
    }

    override fun findAll(): List<User> {
        val list: MutableList<UserDB> = mutableListOf()

        databaseManager.execute(logger) {
            val set = databaseManager.select("SELECT * FROM usuarios")

            while (set.next()) {
                val id = UUID.fromString(set.getString("id"))
                val user = UserDB(
                    id,
                    set.getString("nombre"),
                    set.getString("email"),
                    set.getString("password"),
                    set.getDate("date_alta").toLocalDate(),
                    set.getDate("date_nacimiento").toLocalDate(),
                    set.getString("imageUrl"),
                    set.getBoolean("admin")
                )
                list.add(user)
                logger.info("Se han encontrado los usuarios: $list")
            }
        }

        return list.map { mapToModel(it) ?: return emptyList() }
    }

    override fun update(item: User): User? {
        var returnItem: UserDB? = null
        databaseManager.execute(logger) {
            val modifiedRows = databaseManager
                .update(
                    "UPDATE usuarios set id = ?, nombre = ?, date_alta = ?, password = ?, imageUrl = ?, email = ?, date_nacimiento = ? WHERE id = ?",
                    item.id.toString(),
                    item.name,
                    item.createDate,
                    item.password,
                    item.img,
                    item.email,
                    item.birthDate,
                    item.id.toString()
                )
            if (modifiedRows > 0) {
                returnItem = UserDB.from(item)
                logger.info("Se ha modificado $modifiedRows elementos. ")
            } else {
                logger.info("No se han modificado elementos")
            }
        }
        return mapToModel(returnItem)
    }

    override fun add(item: User): User? {
        var returnItem: UserDB? = null
        databaseManager.execute(logger) {
            databaseManager
                .insert(
                    "Insert into usuarios (id,nombre,date_alta,password,imageUrl,email,date_nacimiento) values (?,?,?,?,?,?,?)",
                    item.id.toString(),
                    item.name,
                    item.createDate,
                    item.password,
                    item.img,
                    item.email,
                    item.birthDate
                )

            returnItem = UserDB.from(item)
            logger.info("Añadido usuario $returnItem")
        }
        return mapToModel(returnItem)
    }

    override fun addToList(item: User, anime: Anime): User? {
        var returnItem: UserDB? = null
        databaseManager.execute(logger) {
            databaseManager
                .insert(
                    "Insert into AnimeLists (idUser,idAnime) values (?,?)",
                    item.id,
                    anime.id
                )
            returnItem = UserDB.from(item)
            logger.info("Se ha añadido a la lista de $item el anime $anime")
        }
        return mapToModel(returnItem)
    }


    override fun removeFromList(item: User, anime: Anime): User? {
        var returnItem: UserDB? = null
        databaseManager.execute(logger) {
            databaseManager
                .delete(
                    "Delete from AnimeLists where idUser = ? and idAnime = ?",
                    item.id,
                    anime.id
                )
            returnItem = UserDB.from(item)
            logger.info("Se ha eliminado el anime $anime de la lista de $item")
        }
        return mapToModel(returnItem)
    }

    override fun delete(id: UUID) {
        if (findById(id) == null) return

        databaseManager.execute(logger) {
            databaseManager.delete("Delete from usuarios where id = ?", id)
            logger.info("Se ha eliminado el usuario ${findById(id)}")
        }
    }

    private fun mapToModel(user: UserDB?): User? {
        if (user == null) return null
        return User(
            user.name,
            user.email,
            user.password,
            user.createDate,
            user.birthDate,
            user.img,
            getAnimeLists(user.id) as MutableList<Anime>,
            user.id,
            user.admin
        )
    }

    override fun getAnimeLists(userId: UUID): List<Anime> {
        val list = mutableListOf<Anime>()
        databaseManager.execute(logger) {
            val listSet = databaseManager
                .select(
                    "SELECT * FROM animeLists " +
                            "INNER JOIN animes on animeLists.idAnime = animes.id " +
                            "WHERE animeLists.idUser = ?", userId.toString()
                )
            while (listSet.next()) {
                val anime = Anime(
                    listSet.getString("title"),
                    listSet.getString("title_english"),
                    listSet.getString("type"),
                    listSet.getInt("episodes"),
                    listSet.getString("status"),
                    listSet.getDate("releaseDate").toLocalDate(),
                    listSet.getString("rating"),
                    listSet.getString("genre").split(","),
                    listSet.getString("imageUrl"),
                    UUID.fromString(listSet.getString("id"))
                )
                list.add(anime)
            }
        }
        return list
    }

}