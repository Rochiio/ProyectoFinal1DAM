package com.example.myanimelist.repositories.admins

import com.example.myanimelist.models.Admin
import com.example.myanimelist.repositories.ICRUDRepository
import java.util.UUID

interface IAdminRepository : ICRUDRepository<UUID, Admin?> {

}