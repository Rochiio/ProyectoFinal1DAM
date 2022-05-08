package com.example.myanimelist.repositories.usersRepositories

import com.example.myanimelist.extensions.execute
import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.User
import java.util.*


class UsersRepository(private val databaseManager: DataBaseManager) : IUsersRepository {

    override fun findByName(name: String): List<User> {
        val list = mutableListOf<User>()
        databaseManager.execute {
            val set = databaseManager.select("SELECT * FROM Usuarios WHERE nombre LIKE ?", "%$name%")

            while (set.next()) {
                val id = UUID.fromString(set.getString("id"))
                val user = User(
                    id,
                    set.getString("nombre"),
                    set.getString("email"),
                    set.getString("password"),
                    set.getDate("date_alta"),
                    set.getDate("date_nacimiento"),
                    set.getString("imageUrl"),
                    getAnimeLists(id)
                )
                list.add(user)
            }
        }
        return list
    }

    override fun findById(id: UUID): User? {
        databaseManager.execute {
            val set = databaseManager.select("SELECT * FROM Usuarios WHERE id = ?", id.toString())

            if (!set.next()) return null

            return User(
                UUID.fromString(set.getString("id")),
                set.getString("nombre"),
                set.getString("email"),
                set.getString("password"),
                set.getDate("date_alta"),
                set.getDate("date_nacimiento"),
                set.getString("imageUrl"),
                getAnimeLists(id)
            )
        }
        return null
    }

    override fun findAll(): List<User> {
        val list: MutableList<User> = mutableListOf()

        databaseManager.execute {
            val set = databaseManager.select("SELECT * FROM Usuarios")

            while (set.next()) {
                val id = UUID.fromString(set.getString("id"))
                val user = User(
                    id,
                    set.getString("nombre"),
                    set.getString("email"),
                    set.getString("password"),
                    set.getDate("date_alta"),
                    set.getDate("date_nacimiento"),
                    set.getString("imageUrl"),
                    getAnimeLists(id)
                )
                list.add(user)
            }
        }

        return list
    }

    override fun update(item: User): User? {
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

            if (modifiedRows > 0) return item
        }
        //default return value
        return null
    }

    override fun add(item: User): User? {
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

            return item
        }
        return null
    }

    override fun delete(id: UUID) {
        if (findById(id) == null) return

        databaseManager.execute {
            databaseManager.delete("Delete from Usuarios where id = ?", id)
        }
    }

    private fun getAnimeLists(userId: UUID) = sequence {
        databaseManager.execute {
            val listSet = databaseManager
                .select(
                    "SELECT * FROM animeLists " +
                            "INNER JOIN animes on animeLists.idAnime = animes.id " +
                            "WHERE animeLists.idUser = ?", userId.toString()
                )

            while (listSet.next()) yield(
                Anime(
                    UUID.fromString(listSet.getString("id")),
                    listSet.getString("title"),
                    listSet.getString("title_english"),
                    listSet.getString("type"),
                    listSet.getInt("episodes"),
                    listSet.getString("status"),
                    listSet.getDate("releaseDate"),
                    listSet.getString("rating"),
                    listSet.getString("genre").split(","),
                    listSet.getString("img")
                )
            )

        }
    }

}