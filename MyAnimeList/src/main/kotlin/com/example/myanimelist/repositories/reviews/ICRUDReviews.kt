package com.example.myanimelist.repositories.reviews

interface ICRUDReviews<T, in ID> {
    /**
     * Añadir reviews al repositorio
     * @param review review a añadir
     * @return la review añadida
     */
    fun add(review: T): T?

    /**
     * Mostrar todas las reviews de un anime
     * @param animeId id del anime a ver todas sus reviews
     * @return lista con todas las reviews de ese anime
     */
    fun findByAnimeId(animeId: ID): Iterable<T>

    /**
     * Devolver todas las reviews que existen en el repositorio
     * @return lista con todas las reviews que existen
     */
    fun findAll(): Iterable<T>
}