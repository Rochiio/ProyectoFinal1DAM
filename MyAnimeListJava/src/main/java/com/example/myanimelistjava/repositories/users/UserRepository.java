package com.example.myanimelistjava.repositories.users;

import com.example.myanimelistjava.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class UserRepository implements IUserRepository{
    @Override
    public User findById(User id) throws SQLException {

        return null;
    }

    @Override
    public List<User> findAll() throws SQLException{
        return null;
    }

    @Override
    public UUID update(UUID item) throws SQLException{
        return null;
    }

    @Override
    public UUID add(UUID item) throws SQLException{
        return null;
    }

    @Override
    public void delete(User id) throws SQLException{

    }
}
