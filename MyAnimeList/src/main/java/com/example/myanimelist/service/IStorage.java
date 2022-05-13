package com.example.myanimelist.service;

/**
 * @param <T> DTO from object to save or load
 * @author JoaquinAyG
 */
public interface IStorage<T> {

    void mkdir();

    void save(T dtoList);

    T load();
}
