package com.example.myanimelist.repositories.animes

import com.example.myanimelist.models.Anime
import com.example.myanimelist.repositories.ICRUDRepository
import java.util.*

interface IAnimeRepository : ICRUDRepository<UUID, Anime>