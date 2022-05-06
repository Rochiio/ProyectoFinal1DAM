package com.example.myanimelist.repositories.usersRepositories

import com.example.myanimelist.models.User
import com.example.myanimelist.repositories.ICRUDRepository
import java.util.*

interface IUsersRepository : ICRUDRepository<UUID, User> {
    fun findByName(name: String): Iterable<User>
}