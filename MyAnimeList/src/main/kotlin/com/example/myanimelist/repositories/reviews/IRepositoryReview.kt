package com.example.myanimelist.repositories.reviews

import com.example.myanimelist.models.Review
import java.util.*


interface IRepositoryReview {
    /**
     * Añadir reviews al repositorio
     * @param review review a añadir
     * @return la review añadida
     */
    fun add(review: Review): Review?

    /**
     * Mostrar todas las reviews de un anime
     * @param animeId id del anime a ver todas sus reviews
     * @return lista con todas las reviews de ese anime
     */
    fun findByAnimeId(animeId: UUID): List<Review>

    /**
     * Actualiza una review
     * @param review review a actualizar
     * @return review si ha sido actualizada
     */
    fun update(review: Review): Review?
}