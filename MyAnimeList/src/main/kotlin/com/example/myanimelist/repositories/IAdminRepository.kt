package com.example.myanimelist.repositories

import com.example.myanimelist.models.Admin
import java.util.UUID

interface IAdminRepository : ICRUDRepository<UUID, Admin?> {

}