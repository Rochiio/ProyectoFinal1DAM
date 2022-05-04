package com.example.myanimelist.repositories.reviews

import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.Reviews

class ReviewsRepository(var db: DataBaseManager): IRepositoryReview{

    override fun addReview(review: Reviews): Reviews {
        TODO("Not yet implemented")
    }

    override fun showReviewsAnime(animeId: String): List<Reviews> {
        TODO("Not yet implemented")
    }
}