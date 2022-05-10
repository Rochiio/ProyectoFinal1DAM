package com.example.myanimelistjava.repositories.reviews;

import java.sql.SQLException;
import java.util.List;

public interface ICRUDReviews<T,ID> {
    /**
     * Añadir reviews al repositorio
     * @param review review a añadir
     * @return la review añadida
     */
     T addReview(T review) throws SQLException;

    /**
     * Mostrar todas las reviews de un anime
     * @param animeId id del anime a ver todas sus reviews
     * @return lista con todas las reviews de ese anime
     */
    List<T> findAllByAnimeId(ID animeId) throws SQLException;

    /**
     * Actualiza una review
     * @param review review a añadir
     * @return review si ha añadido la puntuación
     */
    T addScore(T review) throws SQLException;
}
