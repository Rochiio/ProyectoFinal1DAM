package com.example.myanimelist.repositories


/**
 * @param T es la clave primaria
 * @param K el modelo sobre el que trabaja el repositorio
 */
interface ICRUDRepository<T ,K> {

    fun findById(id: T): K

    fun findAll(): List<K>

    fun update(item: K): K

    fun add(item: K): K

    fun delete(id: T): K

}