package com.example.myanimelist.repositories.users

import com.example.myanimelist.extensions.execute
import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.User
import com.example.myanimelist.repositories.modelsDB.UserDB
import java.util.*


class UsersRepository(private val databaseManager: DataBaseManager) : IUsersRepository {

    override fun findByName(name: String): List<User> {
        val list = mutableListOf<UserDB>()
        databaseManager.execute {
            val set = databaseManager.select("SELECT * FROM Usuarios WHERE nombre LIKE ?", "%$name%")

            while (set.next()) {
                val id = UUID.fromString(set.getString("id"))
                val user = UserDB(
                    id,
                    set.getString("nombre"),
                    set.getString("email"),
                    set.getString("password"),
                    set.getDate("date_alta"),
                    set.getDate("date_nacimiento"),
                    set.getString("imageUrl")
                )
                list.add(user)
            }
        }
        return list.map { mapToDTO(it) ?: return emptyList() }
    }

    override fun findById(id: UUID): User? {
        var returnItem: UserDB? = null

        databaseManager.execute {
            val set = databaseManager.select("SELECT * FROM Usuarios WHERE id = ?", id.toString())

            if (!set.next()) return null

            returnItem = UserDB(
                UUID.fromString(set.getString("id")),
                set.getString("nombre"),
                set.getString("email"),
                set.getString("password"),
                set.getDate("date_alta"),
                set.getDate("date_nacimiento"),
                set.getString("imageUrl")
            )
        }
        return mapToDTO(returnItem)
    }

    override fun findAll(): List<User> {
        val list: MutableList<UserDB> = mutableListOf()

        databaseManager.execute {
            val set = databaseManager.select("SELECT * FROM Usuarios")

            while (set.next()) {
                val id = UUID.fromString(set.getString("id"))
                val user = UserDB(
                    id,
                    set.getString("nombre"),
                    set.getString("email"),
                    set.getString("password"),
                    set.getDate("date_alta"),
                    set.getDate("date_nacimiento"),
                    set.getString("imageUrl")
                )
                list.add(user)
            }
        }

        return list.map { mapToDTO(it) ?: return emptyList() }
    }

    override fun update(item: User): User? {
        var returnItem: UserDB? = null
        databaseManager.execute {
            val modifiedRows = databaseManager
                .update(
                    "UPDATE Usuarios set id = ?, nombre = ?, date_alta = ?, password = ?, imageUrl = ?, email = ?, date_nacimiento = ? WHERE id = ?",
                    item.id.toString(),
                    item.name,
                    item.createDate,
                    item.password,
                    item.img,
                    item.email,
                    item.birthDate,
                    item.id.toString()
                )
            if (modifiedRows > 0) returnItem = UserDB.from(item)
        }
        return mapToDTO(returnItem)
    }

    override fun add(item: User): User? {
        var returnItem: UserDB? = null
        databaseManager.execute {
            databaseManager
                .insert(
                    "Insert into Usuarios (id,nombre,date_alta,password,imageUrl,email,date_nacimiento) values (?,?,?,?,?,?,?)",
                    item.id.toString(),
                    item.name,
                    item.createDate,
                    item.password,
                    item.img,
                    item.email,
                    item.birthDate
                )
            returnItem = UserDB.from(item)
        }
        return mapToDTO(returnItem)
    }

    override fun addToList(item: User, anime: Anime): User? {
        var returnItem: UserDB? = null
        databaseManager.execute {
            databaseManager
                .insert(
                    "Insert into AnimeLists (idUser,idAnime) values (?,?)",
                    item.id,
                    anime.id
                )
            returnItem = UserDB.from(item)
        }
        return mapToDTO(returnItem)
    }


    override fun removeFromList(item: User, anime: Anime): User? {
        var returnItem: UserDB? = null
        databaseManager.execute {
            databaseManager
                .delete(
                    "Delete from AnimeLists where idUser = ? and idAnime = ?",
                    item.id,
                    anime.id
                )
            returnItem = UserDB.from(item)
        }
        return mapToDTO(returnItem)
    }

    override fun delete(id: UUID) {
        if (findById(id) == null) return

        databaseManager.execute {
            databaseManager.delete("Delete from Usuarios where id = ?", id)
        }
    }

    private fun mapToDTO(user: UserDB?): User? {
        if (user == null) return null
        return User(
            user.name,
            user.email,
            user.password,
            user.createDate,
            user.birthDate,
            user.img,
            getAnimeLists(user.id),
            user.id
        )
    }

    private fun getAnimeLists(userId: UUID): List<Anime> {
        val list = mutableListOf<Anime>()
        databaseManager.execute {
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
                    listSet.getDate("releaseDate"),
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