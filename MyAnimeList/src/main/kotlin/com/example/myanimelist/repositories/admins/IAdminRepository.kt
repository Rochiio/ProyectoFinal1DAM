package com.example.myanimelist.repositories.admins

import com.example.myanimelist.models.Admin
import com.example.myanimelist.repositories.animes.ICRUDAnime
import java.util.UUID

interface IAdminRepository : ICRUDAnime<UUID, Admin?> {

}