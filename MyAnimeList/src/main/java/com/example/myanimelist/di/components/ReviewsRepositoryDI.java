package com.example.myanimelist.di.components;

import com.example.myanimelist.controllers.ReviewsController;
import com.example.myanimelist.di.modules.DataBaseManagerModule;
import com.example.myanimelist.repositories.reviews.ReviewsRepository;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = DataBaseManagerModule.class)
public interface ReviewsRepositoryDI {
    ReviewsRepository build();
    void inject(ReviewsController controller);
}
