package com.example.myanimelist.di.modules;

import com.example.myanimelist.repositories.reviews.IRepositoryReview;
import com.example.myanimelist.repositories.reviews.ReviewsRepository;
import dagger.Binds;
import dagger.Module;

import javax.inject.Singleton;

@Module
public interface ReviewsRepositoryModule {
    @Singleton
    @Binds
    IRepositoryReview bindReviewRepository(ReviewsRepository repository);

}
