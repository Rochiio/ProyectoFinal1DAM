package com.example.myanimelist.repositories.reviews

import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.Reviews
import java.sql.ResultSet
import java.sql.SQLException
import java.util.*

class ReviewsRepository(var db: DataBaseManager): IRepositoryReview{

    override fun addReview(review: Reviews): Reviews {
        val query="INSERT INTO reviews VALUES(?,?,null,?,?)"
            db.open()
                var result= db.insert(query,review.idUser,review.idAnime,review.id,review.comment).orElseThrow{ SQLException("Error al insertar review") }
            db.close()
        return review
    }

    override fun showReviewsAnime(animeId: String): List<Reviews> {
        val sql = "SELECT * FROM reviews WHERE idAnime=?"
            db.open()
                val res = db.select(sql,animeId).orElseThrow{SQLException("Error al seleccionar reviews del anime")}
                val list: ArrayList<Reviews> = ArrayList()

                    while (res.next()){
                        var review= Reviews(
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

    //TODO ver como añadir aqui la puntuacion
}