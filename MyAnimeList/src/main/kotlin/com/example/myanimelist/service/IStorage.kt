package com.example.myanimelist.service

/**
 * @param <T> DTO from object to save or load
 * @author JoaquinAyG
</T> */
interface IStorage<T> {
    fun mkdir()
    fun save(dtoList: T)
    fun load(): T?
}