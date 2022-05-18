package com.example.myanimelist.repositories.animeList

interface ICRUDAnimeList <T, ID>{
    fun add(item : T) : T?
    fun delete(item : T) : T?
}