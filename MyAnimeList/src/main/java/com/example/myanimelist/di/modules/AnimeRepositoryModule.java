package com.example.myanimelist.di.modules;

import com.example.myanimelist.repositories.animes.AnimeRepository;
import com.example.myanimelist.repositories.animes.IAnimeRepository;
import dagger.Binds;
import dagger.Module;

import javax.inject.Singleton;

@Module
public interface AnimeRepositoryModule {
    @Singleton
    @Binds
    IAnimeRepository bindAnimeRepository(AnimeRepository repository);
}
