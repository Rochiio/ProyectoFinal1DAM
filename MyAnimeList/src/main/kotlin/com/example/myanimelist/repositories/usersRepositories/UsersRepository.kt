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

            val resultOptional = databaseManager.select("SELECT * FROM Users WHERE id = ?", id.toString())
            val set = resultOptional.orElseThrow { Exception() }

            if (!set.next()) return null

            return User(
                UUID.fromString(set.getString("id")),
                set.getString("name"),
                set.getDate("date_alta"),
                set.getString("password"),
                set.getString("imageUrl"),
                getAnimeLists(id)
            )

        } catch (_: Exception) {

        } finally {
            databaseManager.close()
        }
        //default return value
        return null
    }

    override fun findAll(): List<User> {
        val list: MutableList<User> = mutableListOf()
        try {
            databaseManager.open()

            val resultOptional = databaseManager.select("SELECT * FROM Users")
            val set = resultOptional.orElseThrow { Exception() }

            while (set.next()) {
                val id = UUID.fromString(set.getString("id"))
                list.add(
                    User(
                        id,
                        set.getString("name"),
                        set.getDate("date_alta"),
                        set.getString("password"),
                        set.getString("imageUrl"),
                        getAnimeLists(id)
                    )
                )
            }

        } catch (_: Exception) {

        } finally {
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
                    "UPDATE Users set id = ?, nombre = ?, date_alta = ?, password = ?, imageUrl, WHERE id = ?",
                    item.id.toString(),
                    item.name,
                    item.password,
                    item.img,
                    item.id.toString()
                )

            if (modifiedRows > 0) return item

        } catch (_: Exception) {

        } finally {
            databaseManager.close()
        }
        //default return value
        return null
    }

    override fun add(item: User): User? {
        try {
            databaseManager.open()

            val modifiedRows = databaseManager
                .insert(
                    "Insert into Users (id,nombre,date_alta,password,imageUrl) values (?,?,?,?,?)",
                    item.id.toString(),
                    item.name,
                    item.password,
                    item.img,
                )

            modifiedRows.orElseThrow { Exception() }

            return item

        } catch (_: Exception) {

        } finally {
            databaseManager.close()
        }
        //default return value
        return null
    }

    override fun delete(id: UUID) {
        try {
            databaseManager.open()

            databaseManager.delete("Delete from Users where id = ?", id)

        } catch (_: Exception) {

        } finally {
            databaseManager.close()
        }
    }

    //TODO move to animeRepository
    private fun getAnimeLists(userId: UUID): List<Anime> {
        val list: MutableList<Anime> = mutableListOf()

        try {
            databaseManager.open()

            val listSetOptional = databaseManager.select("SELECT * FROM animeLists WHERE id = ?", userId.toString())

            if (listSetOptional.isEmpty) return emptyList()

            val listSet = listSetOptional.get()
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
        } catch (_: Exception) {

        } finally {
            databaseManager.close()
        }
        return list
    }

}