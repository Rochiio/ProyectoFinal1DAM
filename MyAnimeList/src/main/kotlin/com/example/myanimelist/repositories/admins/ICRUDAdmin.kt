package com.example.myanimelist.repositories.animes


/**
 * @param T es la clave primaria
 * @param K el modelo sobre el que trabaja el repositorio
 */
interface ICRUDAdmin<T ,K> {

    fun findById(id: T): K?

    fun findAll(): Iterable<K>

    fun update(item: K): K?

    fun add(item: K): K?

    fun delete(id: T)

}