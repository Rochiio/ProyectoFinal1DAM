package com.example.myanimelistjava.repositories.anime;

import com.example.myanimelistjava.models.Anime;
import com.example.myanimelistjava.repositories.ICRUDRepository;

import java.util.UUID;

public interface IAnimeRepository extends ICRUDRepository<UUID, Anime> {
}
