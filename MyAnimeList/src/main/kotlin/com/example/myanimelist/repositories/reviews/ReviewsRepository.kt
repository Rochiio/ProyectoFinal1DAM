package com.example.myanimelist.repositories.reviews

import com.example.myanimelist.exceptions.RepositoryException
import com.example.myanimelist.extensions.execute
import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.Review
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.repositories.dto.ReviewDto
import com.example.myanimelist.repositories.usersRepositories.IUsersRepository
import java.util.*

class ReviewsRepository(
    private val databaseManager: DataBaseManager,
    private val animeRepository: IAnimeRepository,
    private val usersRepository: IUsersRepository
) : IRepositoryReview {

    override fun addReview(review: Review): Review? {
        databaseManager.execute {
            val query = "INSERT INTO reviews VALUES(?,?,0,?,?)"
            databaseManager.insert(query, review.user.id, review.anime.id, review.id, review.comment)
            return review
        }
        return null
    }

    @kotlin.jvm.Throws(RepositoryException::class)
    override fun showReviewsAnime(animeId: UUID): List<Review> {
        val list: MutableList<ReviewDto> = mutableListOf()

        databaseManager.execute {
            val sql = "SELECT * FROM reviews WHERE idAnime=?"
            val res =
                databaseManager.select(sql, animeId.toString())

            while (res.next()) {
                val review = ReviewDto(
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
                it.id,
                animeRepository.findById(it.idAnime) ?: throw RepositoryException("Couldn't load anime for review"),
                usersRepository.findById(it.idUser) ?: throw RepositoryException("Couldn't load user for review"),
                it.score,
                it.comment
            )
        }
    }

    override fun addScore(review: Review): Review? {
        databaseManager.execute {
            val query = "INSERT INTO reviews VALUES(?,?,?,?,null)"
            databaseManager.insert(query, review.user.id, review.anime.id, review.score, review.id)
            return review
        }
        return null
    }


}