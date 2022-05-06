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
        usersRepository.add(
            getTestingUser(id)
        )
        val user = usersRepository.findbyId(id)

        assert(user?.id == id)
    }

    @Test
    fun findByName() {
        val id = UUID.randomUUID()
        usersRepository.add(
            getTestingUser(id)
        )
        val users = usersRepository.findByName("Pep")

        assert(users.map { it.id }.contains(id))
    }

    @Test
    fun findAll() {
        val ids = listOf<UUID>(UUID.randomUUID(), UUID.randomUUID())
        usersRepository.add(
            getTestingUser(ids[0])
        )
        usersRepository.add(
            getTestingUser(ids[1])
        )
        val users = usersRepository.findAll()

        assert(users.map { it.id }.containsAll(ids))
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
        usersRepository.add(
            getTestingUser(id)
        )

        val user = usersRepository.findbyId(id)

        assert(user?.id == id)
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