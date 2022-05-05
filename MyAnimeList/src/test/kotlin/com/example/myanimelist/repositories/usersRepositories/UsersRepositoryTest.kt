package com.example.myanimelist.repositories.usersRepositories

import com.example.myanimelist.models.User
import org.junit.jupiter.api.Test
import java.sql.Date
import java.util.*

internal class UsersRepositoryTest {
    private val usersRepository = UsersRepository()

    @Test
    fun findById() {
        val id = UUID.randomUUID()
        usersRepository.add(
            User(id, "Pepe", Date(Date().time), "123", "img", mutableListOf())
        )
        val user = usersRepository.findbyId(id)

        assert(user?.id == id)
    }

    @Test
    fun findAll() {
        val ids = listOf<UUID>(UUID.randomUUID(), UUID.randomUUID())
        usersRepository.add(
            User(ids[0], "Pepe", Date(Date().time), "123", "img", mutableListOf())
        )
        usersRepository.add(
            User(ids[1], "Pepe2", Date(Date().time), "123", "img", mutableListOf())
        )
        val users = usersRepository.findAll()

        assert(users.map { it.id }.containsAll(ids))
    }

    @Test
    fun update() {
        val id = UUID.randomUUID()
        usersRepository.add(
            User(id, "Pepe", Date(Date().time), "123", "img", mutableListOf())
        )
        usersRepository.update(
            User(id, "PepeUpdated", Date(Date().time), "123", "img", mutableListOf())
        )

        val user = usersRepository.findbyId(id)

        assert(user?.name == "PepeUpdated")
    }

    @Test
    fun add() {
        val id = UUID.randomUUID()
        usersRepository.add(
            User(id, "Pepe", Date(Date().time), "123", "img", mutableListOf())
        )

        val user = usersRepository.findbyId(id)

        assert(user?.id == id)
    }

    @Test
    fun delete() {
        val id = UUID.randomUUID()
        usersRepository.add(
            User(id, "Pepe", Date(Date().time), "123", "img", mutableListOf())
        )

        val user = usersRepository.findbyId(id)
        usersRepository.delete(id)
        val notUser = usersRepository.findbyId(id)

        assert(user != null && notUser == null)
    }
}