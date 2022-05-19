package com.example.myanimelist.repositories.animeList

import java.util.*

interface ICRUDAnimeList <T, K>{
    fun add(item : T, user : K) : T?
    fun delete(item : T, user : K) : T?

    fun findByUserId(user : K) : List<UUID>?
}