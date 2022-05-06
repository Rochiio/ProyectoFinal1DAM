package com.example.myanimelist.repositories.usersRepositories

import com.example.myanimelist.models.User
import org.junit.jupiter.api.Test
import java.sql.Date
import java.util.*

internal class UsersRepositoryTest {
    private val usersRepository = UsersRepository

    private fun getTestingUser(id: UUID) =
        User(id, "Pepe", "asdasd@gmail.com", "123", Date(Date().time), Date(Date().time), "img", mutableListOf())

    @Test
    fun findById() {
        val id = UUID.randomUUID()
        val insertedUser = usersRepository.add(
            getTestingUser(id)
        )
        val user = usersRepository.findbyId(id)

        assert(user == insertedUser)
    }

    @Test
    fun findByName() {
        val id = UUID.randomUUID()
        val user = usersRepository.add(
            getTestingUser(id)
        )
        val users = usersRepository.findByName("Pep")

        assert(users.contains(user))
    }

    @Test
    fun findAll() {
        val ids = listOf<UUID>(UUID.randomUUID(), UUID.randomUUID())
        val user1 = usersRepository.add(
            getTestingUser(ids[0])
        )
        val user2 = usersRepository.add(
            getTestingUser(ids[1])
        )
        val users = usersRepository.findAll()

        assert(users.containsAll(listOf(user1, user2)))
    }

    @Test
    fun update() {
        val id = UUID.randomUUID()
        usersRepository.add(
            getTestingUser(id)
        )
        usersRepository.update(
            getTestingUser(id).also { it.name = "PepeUpdated" }
        )

        val user = usersRepository.findbyId(id)

        assert(user?.name == "PepeUpdated")
    }

    @Test
    fun add() {
        val id = UUID.randomUUID()
        val userCreated = usersRepository.add(
            getTestingUser(id)
        )

        val user = usersRepository.findbyId(id)

        assert(user == userCreated)
    }

    @Test
    fun delete() {
        val id = UUID.randomUUID()
        usersRepository.add(
            getTestingUser(id)
        )

        val user = usersRepository.findbyId(id)
        usersRepository.delete(id)
        val notUser = usersRepository.findbyId(id)

        assert(user != null && notUser == null)
    }
}