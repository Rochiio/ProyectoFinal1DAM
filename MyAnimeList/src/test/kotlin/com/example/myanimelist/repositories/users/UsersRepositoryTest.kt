package com.example.myanimelist.repositories.users

import com.example.myanimelist.di.dataBaseManagerModule
import com.example.myanimelist.di.dataBaseManagerModuleDev
import com.example.myanimelist.di.usersRepositoryModule
import com.example.myanimelist.extensions.execute
import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.utilities.*
import com.example.myanimelist.utils.Properties
import org.junit.Rule
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.get
import java.util.*


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsersRepositoryTest : KoinTest {

    private lateinit var usersRepository: IUsersRepository

    @BeforeAll

    fun start(){
        startKoin {
            modules(
                dataBaseManagerModuleDev,
                usersRepositoryModule
            )
        }
        usersRepository = get()

    }
    @BeforeEach
    fun setUp() {

        val db: DataBaseManager = get<DataBaseManager>()
        db.execute {
            initData(Properties.SCRIPT_FILE_DATABASE, false)
        }
    }

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