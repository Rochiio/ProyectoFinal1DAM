package com.example.myanimelist.repositories.reviews

interface CRUDReview<T,ID> {
    /**
     * Añadir reviews al repositorio
     * @param review review a añadir
     * @return la review añadida
     */
    fun addReview(review:T):T

    /**
     * Mostrar todas las reviews de un anime
     * @param animeId id del anime a ver todas sus reviews
     * @return lista con todas las reviews de ese anime
     */
    fun showReviewsAnime(animeId: ID):List<T>

    //TODO mirar como añadir aqui tmb el add puntuación

}