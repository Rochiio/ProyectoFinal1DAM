package com.example.myanimelist.repositories.reviews

import com.example.myanimelist.exceptions.RepositoryException
import com.example.myanimelist.extensions.execute
import com.example.myanimelist.manager.DataBaseManager
import com.example.myanimelist.models.Review
import com.example.myanimelist.repositories.animes.AnimeRepository
import com.example.myanimelist.repositories.animes.IAnimeRepository
import com.example.myanimelist.repositories.modelsDB.ReviewDB
import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.repositories.users.UsersRepository
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import java.util.*
import javax.inject.Inject

//TODO Review reviews
class ReviewsRepository
@Inject constructor(
    private val databaseManager: DataBaseManager,
    private val animeRepository: AnimeRepository,
    private val usersRepository: UsersRepository
) : IRepositoryReview{
    val logger : Logger = LogManager.getLogger(ReviewsRepository::class)


    override fun add(review: Review): Review? {
        databaseManager.execute(logger) {
            val query = "INSERT INTO reviews VALUES(?,?,?,?,?)"
            databaseManager.insert(query, review.user.id, review.anime.id, review.score, review.id, review.comment)
            logger.info("Añadida review $review")
            return review
        }
        return null
    }

    @kotlin.jvm.Throws(RepositoryException::class)
    override fun findByAnimeId(animeId: UUID): List<Review> {
        val list: MutableList<ReviewDB> = mutableListOf()

        databaseManager.execute(logger) {
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

    override fun findAll(): Iterable<Review> {
        val list: MutableList<ReviewDB> = mutableListOf()

        databaseManager.execute(logger) {
            val sql = "SELECT * FROM reviews"
            val res =
                databaseManager.select(sql)

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