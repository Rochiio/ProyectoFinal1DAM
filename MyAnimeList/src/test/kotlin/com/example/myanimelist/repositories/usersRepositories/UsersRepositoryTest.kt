package com.example.myanimelist.repositories.usersRepositories

import com.example.myanimelist.models.User
import com.example.myanimelist.modules.RepositoriesModules.repositoryModule
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.test.inject
import org.koin.test.junit5.AutoCloseKoinTest
import java.sql.Date
import java.util.*

internal class UsersRepositoryTest : AutoCloseKoinTest() {
    private val usersRepository by inject<IUsersRepository>()

    init {
        startKoin { modules(repositoryModule) }
    }

    private fun getTestingUser(id: UUID) =
        User(id, "Pepe", "asdasd@gmail.com", "123", Date(Date().time), Date(Date().time), "img", mutableListOf())

    @Test
    fun findById() {
        val id = UUID.randomUUID()
        val insertedUser = usersRepository.add(
            getTestingUser(id)
        )
        val user = usersRepository.findById(id)

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
        val users = usersRepository.findAll().toList()

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

        val user = usersRepository.findById(id)

        assert(user?.name == "PepeUpdated")
    }

    @Test
    fun add() {
        val id = UUID.randomUUID()
        val userCreated = usersRepository.add(
            getTestingUser(id)
        )

        val user = usersRepository.findById(id)

        assert(user == userCreated)
    }

    @Test
    fun delete() {
        val id = UUID.randomUUID()
        usersRepository.add(
            getTestingUser(id)
        )

        val user = usersRepository.findById(id)
        usersRepository.delete(id)
        val notUser = usersRepository.findById(id)

        assert(user != null && notUser == null)
    }
}