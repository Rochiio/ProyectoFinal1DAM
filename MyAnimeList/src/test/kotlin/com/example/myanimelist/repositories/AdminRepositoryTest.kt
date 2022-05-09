package com.example.myanimelist.repositories

import com.example.myanimelist.models.Admin
import com.example.myanimelist.modules.RepositoriesModules.repositoryModule
import com.example.myanimelist.repositories.admins.IAdminRepository
import com.example.myanimelist.utilities.DataDB
import com.example.myanimelist.utilities.DataDB.getTestingAdmin
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.test.junit5.AutoCloseKoinTest

internal class AdminRepositoryTest : AutoCloseKoinTest() {
    private val adminRepository by inject<IAdminRepository>()

    init {
        startKoin { modules(repositoryModule) }
    }

    @BeforeEach
    fun deleteAll() {
        DataDB.deleteAll("admins")
    }

    @Test
    fun findbyIdTest() {
        val admin = getTestingAdmin(1)
        adminRepository.add(admin)
        assertTrue(admin == adminRepository.findById(admin.id))
    }

    @Test
    fun findAllTest() {
        val adminsEmpty = adminRepository.findAll()
        val emptyList: MutableList<Admin> = mutableListOf()
        val admin1 = adminRepository.add(getTestingAdmin(1))
        val admin2 = adminRepository.add(getTestingAdmin(2))
        val admin3 = adminRepository.add(getTestingAdmin(3))

        val adminsFilled = adminRepository.findAll()

        assertAll(
            { assertEquals(adminsEmpty, emptyList) },
            { assertTrue(adminsFilled.contains(admin1)) },
            { assertTrue(adminsFilled.contains(admin2)) },
            { assertTrue(adminsFilled.contains(admin3)) }
        )

    }

    @Test
    fun updateTest() {
        val admin = getTestingAdmin(1)

        val newAdmin = Admin(
             admin.id,
            "Antonio",
            "asdasd@gmail.com", "abc",
            admin.createDate, admin.birthDate
        )

        adminRepository.add(admin)
        val updatedAdmin = adminRepository.update(newAdmin)
        if (updatedAdmin != null) {
            assertAll(
                { assertEquals(admin.id, updatedAdmin.id) },
                { assertEquals(newAdmin.name, updatedAdmin.name) },
                { assertEquals(newAdmin.email, updatedAdmin.email) },
                { assertEquals(newAdmin.password, updatedAdmin.password) }
            )
        }
    }

    @Test
    fun addTest() {
        val aux = getTestingAdmin(1)
        val admin = adminRepository.add(aux)
        println(admin)
        assertAll(
            { assertTrue(aux == admin) }
        )
    }

    @Test
    fun deleteTest() {
        val listaVacia = adminRepository.findAll()

        val admin = getTestingAdmin(1)
        adminRepository.add(admin)
        adminRepository.delete(admin.id)
        val lista = adminRepository.findAll()

        assertEquals(listaVacia, lista)
    }
}