package com.example.myanimelist.repositories.users

import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.User
import com.example.myanimelist.repositories.ICRUDRepository
import java.util.*

interface IUsersRepository : ICRUDRepository<UUID, User> {
    fun findByName(name: String): Iterable<User>
    fun addToList(item: User, anime: Anime): User?
    fun removeFromList(item: User, anime: Anime): User?
}