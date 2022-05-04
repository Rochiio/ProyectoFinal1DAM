package com.example.myanimelist.repositories

import com.example.myanimelist.models.User
import java.util.*

interface IUsersRepository : ICRUDRepository<UUID, User>