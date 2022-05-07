package com.example.myanimelist.repositories.reviews

import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.Reviews
import java.sql.SQLException
import java.util.*

class ReviewsRepository(private var databaseManager: DataBaseManager) : IRepositoryReview {

    override fun addReview(review: Reviews): Reviews {
        val query = "INSERT INTO reviews VALUES(?,?,0,?,?)"
        databaseManager.open()
        databaseManager.insert(query, review.idUser, review.idAnime, review.id, review.comment)
            .orElseThrow { SQLException("Error al insertar review") }
        databaseManager.close()
        return review
    }

    override fun showReviewsAnime(animeId: UUID): List<Reviews> {
        val sql = "SELECT * FROM reviews WHERE idAnime=?"
        databaseManager.open()
        val res =
            databaseManager.select(sql, animeId.toString())
                .orElseThrow { SQLException("Error al seleccionar reviews del anime") }
        val list: ArrayList<Reviews> = ArrayList()

        while (res.next()) {
            val review = Reviews(
                UUID.fromString(res.getString("id")),
                UUID.fromString(res.getString("idAnime")),
                UUID.fromString(res.getString("idUser")),
                res.getInt("score"),
                res.getString("review")
            )
            list.add(review)
        }
        databaseManager.close()
        return list
    }

    override fun addScore(review: Reviews): Reviews {
        val query = "INSERT INTO reviews VALUES(?,?,?,?,null)"
        databaseManager.open()
        databaseManager.insert(query, review.idUser, review.idAnime, review.score, review.id)
            .orElseThrow { SQLException("Error al insertar puntuación") }
        databaseManager.close()
        return review
    }


}