package com.example.myanimelist.repositories.animeList

interface ICRUDAnimeList<T, in K> {
    fun add(item: T, user: K): T?
    fun delete(item: T, user: K): T?

    fun findByUserId(user: K): List<T>
}