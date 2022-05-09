package com.example.myanimelistjava.repositories;

public interface ICRUDRepository <T, K>{

    K findById(T id);

    Iterable<K> findAll();

    K update(K item);

    K add(K item);

    void delete(T id);
}
