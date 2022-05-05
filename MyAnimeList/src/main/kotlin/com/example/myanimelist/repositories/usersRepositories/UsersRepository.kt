package com.example.myanimelist.repositories.usersRepositories

import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.*
import java.util.*


class UsersRepository : IUsersRepository {
    //TODO fill catch blocks with logger
    //TODO Throw custom exceptions

    private val databaseManager: DataBaseManager = DataBaseManager.getInstance()

    override fun findbyId(id: UUID): User? {
        try {
            databaseManager.open()

            val set = databaseManager.select("SELECT * FROM Usuarios WHERE id = ?", id.toString()).get()

            if (!set.next()) return null

            return User(
                UUID.fromString(set.getString("id")),
                set.getString("nombre"),
                set.getDate("date_alta"),
                set.getString("password"),
                set.getString("imageUrl"),
                getAnimeLists(id)
            )

        }
        catch (ex: Exception) {
            error(ex)
        }
        finally {
            databaseManager.close()
        }
    }

    override fun findAll(): List<User> {
        val list: MutableList<User> = mutableListOf()
        try {
            databaseManager.open()

            val set = databaseManager.select("SELECT * FROM Usuarios").get()

            while (set.next()) {
                val id = UUID.fromString(set.getString("id"))
                list.add(
                    User(
                        id,
                        set.getString("nombre"),
                        set.getDate("date_alta"),
                        set.getString("password"),
                        set.getString("imageUrl"),
                        getAnimeLists(id)
                    )
                )
            }

        }
        catch (ex: Exception) {
            error(ex)
        }
        finally {
            databaseManager.close()
        }
        //default return value
        return list
    }

    override fun update(item: User): User? {
        try {
            databaseManager.open()

            val modifiedRows = databaseManager
                .update(
                    "UPDATE Usuarios set id = ?, nombre = ?, date_alta = ?, password = ?, imageUrl = ? WHERE id = ?",
                    item.id.toString(),
                    item.name,
                    item.createDate,
                    item.password,
                    item.img,
                    item.id.toString()
                )

            if (modifiedRows > 0) return item

        }
        catch (ex: Exception) {
            error(ex)
        }
        finally {
            databaseManager.close()
        }
        //default return value
        return null
    }

    //TODO add lists of animes
    override fun add(item: User): User? {
        try {
            databaseManager.open()

            databaseManager
                .insert(
                    "Insert into Usuarios (id,nombre,date_alta,password,imageUrl) values (?,?,?,?,?)",
                    item.id.toString(),
                    item.name,
                    item.createDate,
                    item.password,
                    item.img,
                )

            return item

        }
        catch (ex: Exception) {
            error(ex)
        }
        finally {
            databaseManager.close()
        }
        //Could throw during insert
        @Suppress("UNREACHABLE_CODE")
        return null
    }

    override fun delete(id: UUID) {
        try {
            if (findbyId(id) == null) return

            databaseManager.open()
            databaseManager.delete("Delete from Usuarios where id = ?", id)

        }
        catch (ex: Exception) {
            error(ex)
        }
        finally {
            databaseManager.close()
        }
    }

    //TODO move to animeRepository
    //TODO test
    private fun getAnimeLists(userId: UUID): List<Anime> {
        val list: MutableList<Anime> = mutableListOf()

        try {

            val listSet = databaseManager
                .select(
                    "SELECT * FROM animeLists " +
                            "INNER JOIN animes on animeLists.idAnime = animes.id " +
                            "WHERE animeLists.idUser = ?", userId.toString()
                ).get()

            while (listSet.next())
                list.add(
                    Anime(
                        UUID.fromString(listSet.getString("id")),
                        listSet.getString("title"),
                        listSet.getString("title_english"),
                        Type.valueOf(listSet.getString("type")),
                        listSet.getInt("episodes"),
                        Status.valueOf(listSet.getString("status")),
                        listSet.getDate("releaseDate"),
                        listSet.getString("rating"),
                        listSet.getString("genre").split(",").map { Genre.valueOf(it) }
                    ))
        }
        catch (ex: Exception) {
            error(ex)
        }
        return list
    }

}