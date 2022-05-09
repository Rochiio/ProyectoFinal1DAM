package com.example.myanimelistjava.repositories;

import java.sql.SQLException;

public interface ICRUDRepository <T, K>{

    K findById(T id) throws SQLException;

    Iterable<K> findAll() throws SQLException;

    K update(K item) throws SQLException;

    K add(K item) throws SQLException;

    void delete(T id) throws SQLException;

}
