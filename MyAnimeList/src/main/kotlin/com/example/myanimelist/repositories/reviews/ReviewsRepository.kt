package com.example.myanimelist.repositories.reviews

import com.example.myanimelist.exceptions.RepositoryException
import com.example.myanimelist.extensions.execute
import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.Reviews
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.repositories.usersRepositories.IUsersRepository
import java.util.*

class ReviewsRepository(
    private val databaseManager: DataBaseManager,
    private val animeRepository: IAnimeRepository,
    private val usersRepository: IUsersRepository
) : IRepositoryReview {

    override fun addReview(review: Reviews): Reviews? {
        databaseManager.execute {
            val query = "INSERT INTO reviews VALUES(?,?,0,?,?)"
            databaseManager.insert(query, review.user.id, review.anime.id, review.id, review.comment)
            return review
        }
        return null
    }

    override fun showReviewsAnime(animeId: UUID): List<Reviews> {
        val list: MutableList<Reviews> = mutableListOf()
        databaseManager.execute {
            val sql = "SELECT * FROM reviews WHERE idAnime=?"
            val res =
                databaseManager.select(sql, animeId.toString())

            while (res.next()) {
                val review = Reviews(
                    UUID.fromString(res.getString("id")),
                    animeRepository.findById(UUID.fromString(res.getString("idAnime")))
                        ?: throw RepositoryException("Anime not found for review"),
                    usersRepository.findById(UUID.fromString(res.getString("idUser")))
                        ?: throw RepositoryException("User not found for review"),
                    res.getInt("score"),
                    res.getString("review")
                )
                list.add(review)
            }
            return list
        }
        return emptyList()
    }

    override fun addScore(review: Reviews): Reviews? {
        databaseManager.execute {
            val query = "INSERT INTO reviews VALUES(?,?,?,?,null)"
            databaseManager.insert(query, review.user.id, review.anime.id, review.score, review.id)
            return review
        }
        return null
    }


}