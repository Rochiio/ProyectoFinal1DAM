package com.example.myanimelistjava.repositories.admin;

import com.example.myanimelistjava.managers.DataBaseManager;
import com.example.myanimelistjava.models.Admin;
import com.example.myanimelistjava.models.User;
import com.example.myanimelistjava.repositories.users.IUserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


public class AdminRepository implements IAdminRepository {

    private DataBaseManager db;

    @Override
    public Admin findById(Admin id) throws SQLException {
        String query = "select * from admins where id = ?";

/*
            ResultSet result = db.select(query, id);
            if (result.next()) {
                Admin admin = new Admin(

                );
*
                return admin;

        }*/
        return null;
    }

    @Override
    public List<Admin> findAll() throws SQLException {
        return null;
    }

    @Override
    public UUID update(UUID item) throws SQLException {
        return null;
    }

    @Override
    public UUID add(UUID item) throws SQLException {
        return null;
    }

    @Override
    public void delete(Admin id) throws SQLException {

    }
}
