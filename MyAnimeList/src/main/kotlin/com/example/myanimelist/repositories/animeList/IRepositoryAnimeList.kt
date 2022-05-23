package com.example.myanimelist.repositories.animeList

import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.User

interface IRepositoryAnimeList : ICRUDAnimeList<Anime, User> {
}