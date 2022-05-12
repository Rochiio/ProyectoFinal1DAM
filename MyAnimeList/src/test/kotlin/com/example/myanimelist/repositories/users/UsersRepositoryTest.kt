package com.example.myanimelist.repositories.users

//import com.example.myanimelist.modules.repositoryModule
import com.example.myanimelist.manager.DataBaseManager
import com.example.myanimelist.repositories.animes.AnimeRepository
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.utilities.DataDB
import com.example.myanimelist.utilities.DataDB.getTestingAnime
import com.example.myanimelist.utilities.DataDB.getTestingUser
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.test.inject
import org.koin.test.junit5.AutoCloseKoinTest
import java.util.*
import kotlin.test.assertNull

class UsersRepositoryTest : AutoCloseKoinTest() {
    private val usersRepository : UsersRepository = UsersRepository(DataBaseManager.getInstance())
    private val animeRepository : AnimeRepository = AnimeRepository(DataBaseManager.getInstance())


    @AfterEach
    fun deleteAll() {
        DataDB.deleteAll("Usuarios")
        DataDB.deleteAll("animeLists")
    }


    @Test
    fun findById() {
        val user = getTestingUser()

        usersRepository.add(user)
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
        usersRepository.add(user)
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
        val user1 = usersRepository.add(
            getTestingUser()
        )
        val user2 = usersRepository.add(
            getTestingUser()
        )
        val users = usersRepository.findAll().toList()

        assert(users.containsAll(listOf(user1, user2)))
    }

    @Test
    fun update() {
        val userTest = getTestingUser()

        usersRepository.add(userTest)
        val userUpdated = usersRepository.update(
            userTest.also { it.name = "PepeUpdated" }
        )

        val user = usersRepository.findById(userTest.id)

        assert(user?.name == userUpdated?.name)
    }

    @Test
    fun updateShouldReturnNull() {
        val user = usersRepository.update(
            getTestingUser().also { it.name = "PepeUpdated" }
        )

        assert(user == null)
    }

    @Test
    fun add() {
        val userTest = getTestingUser()

        val userCreated = usersRepository.add(userTest)

        val user = usersRepository.findById(userTest.id)
        assert(user == userCreated)
    }

    @Test
    fun addToList() {
        val userCreated = usersRepository.add(getTestingUser()) ?: throw Exception()
        val animeCreated = animeRepository.add(getTestingAnime()) ?: throw Exception()

        val user = usersRepository.addToList(userCreated, animeCreated)
        assert(user?.myList?.first() == animeCreated)
    }

    @Test
    fun removeFromList() {
        val userCreated = usersRepository.add(getTestingUser()) ?: throw Exception()
        val animeCreated = animeRepository.add(getTestingAnime()) ?: throw Exception()

        val list = usersRepository.addToList(userCreated, animeCreated)?.myList?.toList()
        val listEmpty = usersRepository.removeFromList(userCreated, animeCreated)?.myList?.toList()

        assert(list?.any()!! && !listEmpty?.any()!!)
    }

    @Test
    fun addShouldReturnNull() {
        val user = getTestingUser()
        usersRepository.add(user)

        val userCreatedNull = usersRepository.add(user)

        assert(userCreatedNull == null)
    }

    @Test
    fun delete() {
        val userTest = getTestingUser()
        usersRepository.add(userTest)

        val user = usersRepository.findById(userTest.id)
        usersRepository.delete(userTest.id)
        val notUser = usersRepository.findById(userTest.id)

        assert(user != null && notUser == null)
    }
}