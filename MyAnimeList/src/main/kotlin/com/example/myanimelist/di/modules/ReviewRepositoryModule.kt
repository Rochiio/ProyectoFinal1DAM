package com.example.myanimelist.di.modules

import com.example.myanimelist.repositories.reviews.IRepositoryReview
import com.example.myanimelist.repositories.reviews.ReviewsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ReviewRepositoryModule {
    @Singleton
    @Binds
    fun bindReviewRepository(repository: ReviewsRepository) : IRepositoryReview
}