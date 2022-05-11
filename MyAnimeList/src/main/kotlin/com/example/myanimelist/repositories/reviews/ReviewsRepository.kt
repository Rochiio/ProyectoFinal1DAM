package com.example.myanimelist.repositories.reviews

import com.example.myanimelist.exceptions.RepositoryException
import com.example.myanimelist.extensions.execute
import com.example.myanimelist.manager.DataBaseManager
import com.example.myanimelist.models.Review
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.repositories.modelsDB.ReviewDB
import com.example.myanimelist.repositories.users.IUsersRepository
import java.util.*

class ReviewsRepository(
    private val databaseManager: DataBaseManager,
    private val animeRepository: IAnimeRepository,
    private val usersRepository: IUsersRepository
) : IRepositoryReview {

    override fun addReview(review: Review): Review? {
        databaseManager.execute {
            val query = "INSERT INTO reviews VALUES(?,?,?,?,?)"
            databaseManager.insert(query, review.user.id, review.anime.id,review.score, review.id, review.comment)
            return review
        }
        return null
    }

    @kotlin.jvm.Throws(RepositoryException::class)
    override fun findByAnimeId(animeId: UUID): List<Review> {
        val list: MutableList<ReviewDB> = mutableListOf()

        databaseManager.execute {
            val sql = "SELECT * FROM reviews WHERE idAnime = ?"
            val res =
                databaseManager.select(sql, animeId.toString())

            while (res.next()) {
                val review = ReviewDB(
                    UUID.fromString(res.getString("id")),
                    UUID.fromString(res.getString("idAnime")),
                    UUID.fromString(res.getString("idUser")),
                    res.getInt("score"),
                    res.getString("review")
                )
                list.add(review)
            }
        }

        return list.map {
            Review(
                animeRepository.findById(it.idAnime) ?: return emptyList(),
                usersRepository.findById(it.idUser) ?: return emptyList(),
                it.score,
                it.comment,
                it.id
            )
        }
    }

}