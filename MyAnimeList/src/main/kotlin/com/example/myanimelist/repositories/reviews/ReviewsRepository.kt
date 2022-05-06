package com.example.myanimelist.repositories.reviews

import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.Reviews
import java.sql.SQLException
import java.util.*

class ReviewsRepository(var db: DataBaseManager) : IRepositoryReview {

    override fun addReview(review: Reviews): Reviews {
        val query = "INSERT INTO reviews VALUES(?,?,0,?,?)"
        db.open()
        var result = db.insert(query, review.idUser, review.idAnime, review.id, review.comment)
            .orElseThrow { SQLException("Error al insertar review") }
        db.close()
        return review
    }

    override fun showReviewsAnime(animeId: UUID): List<Reviews> {
        val sql = "SELECT * FROM reviews WHERE idAnime=?"
        db.open()
        val res = db.select(sql, animeId.toString()).orElseThrow { SQLException("Error al seleccionar reviews del anime") }
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
        db.close()
        return list
    }

    override fun addScore(review: Reviews): Reviews {
        val query = "INSERT INTO reviews VALUES(?,?,?,?,null)"
        db.open()
        var result = db.insert(query, review.idUser, review.idAnime, review.score, review.id)
            .orElseThrow { SQLException("Error al insertar puntuación") }
        db.close()
        return review
    }


}