package com.example.myanimelist.service;

import java.util.Optional;

/**
 * @param <T> DTO from object to save or load
 * @author JoaquinAyG
 */
public interface IStorage<T> {

    void mkdir();

    void save(T dtoList);

    Optional<T> load();
}
