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
    private AdminRepository instance;

    public AdminRepository getInstance(){
        if(instance != null)
            return instance;
        instance = new AdminRepository(DataBaseManager db);
        return instance;
    }

    private AdminRepository(DataBaseManager db){
       // db = db.getInstance();
    }
    @Override
    public Admin findById(Admin id) throws SQLException {
        String query = "select * from admins where id = ?";
        db.open();
        ResultSet result = db.select(query, id);
        if (result.next()) {

            Admin admin = new Admin(
                    UUID.fromString(result.getString("id")),
                    result.getString("name"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getString("createDate"),
                    result.getString("birthDate")
            );
            db.close();
            return admin;
        }
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
