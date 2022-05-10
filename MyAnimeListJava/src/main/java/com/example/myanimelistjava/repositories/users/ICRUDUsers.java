package com.example.myanimelistjava.repositories.users;

import java.sql.SQLException;
import java.util.List;

public interface ICRUDUsers <T, K>{

    public T findById(T id) throws SQLException;
    public List<T> findAll()throws SQLException;
    public K update(K item)throws SQLException;

    public K add(K item)throws SQLException;

    public void delete(T id)throws SQLException;

}
