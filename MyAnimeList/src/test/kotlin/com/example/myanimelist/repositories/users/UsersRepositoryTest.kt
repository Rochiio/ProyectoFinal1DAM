package com.example.myanimelist.repositories.users

import com.example.myanimelist.managers.DependenciesManager.getUsersRepo
import com.example.myanimelist.utilities.*
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import java.util.*


class UsersRepositoryTest {
    private val usersRepository = getUsersRepo()

    @BeforeEach
    fun setUp() = resetDb()

    @Test
    fun findById() {
        val user = getTestingUser()

        val insertedUser = usersRepository.findById(user.id)

        assert(user == insertedUser)
    }

    @Test
    fun findByIdShouldReturnNull() {
        val nullUser = usersRepository.findById(UUID.randomUUID())

        assertNull(nullUser)
    }

    @Test
    fun findByName() {
        val user = getTestingUser()
        val users = usersRepository.findByName("Pep")

        assert(users.contains(user))
    }

    @Test
    fun findByNameShouldReturnEmpty() {
        val users = usersRepository.findByName("asdsadad")
        assert(!users.any())
    }

    @Test
    fun findAll() {
        val user = getTestingUser()
        val users = usersRepository.findAll().toList()

        assert(users.containsAll(listOf(user)))
    }

    @Test
    fun update() {
        val userTest = getTestingUserUpdate()

        val userUpdated = usersRepository.update(
            userTest.also { it.name = "PepeUpdated" }
        )

        assertNotNull(userUpdated?.name)
    }

    @Test
    fun updateShouldReturnNull() {
        val user = usersRepository.update(
            getNewTestingUser().also { it.name = "PepeUpdated" }
        )

        assert(user == null)
    }

    @Test
    fun add() {
        val userTest = getNewTestingUser()
        val userCreated = usersRepository.add(userTest)
        assertNotNull(userCreated)
    }

    @Test
    @Order(0)
    fun addToList() {
        val user = getTestingUser()
        val anime = getTestingAnime()

        val userWithList = usersRepository.addToList(user, anime)
        assertNotNull(userWithList)
    }

    @Test
    @Order(1)
    fun removeFromList() {
        val user = getTestingUser()
        val anime = getTestingAnime()

        val userWithList = usersRepository.removeFromList(user, anime)

        assertNotNull(userWithList)
    }

    @Test
    fun addShouldReturnNull() {
        val user = getTestingUser()

        val userCreatedNull = usersRepository.add(user)

        assertNull(userCreatedNull)
    }

    @Test
    fun delete() {
        val user = getTestingUserDelete()
        usersRepository.delete(user.id)
        assertNull(usersRepository.findById(user.id))
    }
}