package com.example.myanimelistjava.repositories.admin;

import com.example.myanimelistjava.managers.DataBaseManager;
import com.example.myanimelistjava.models.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


public class AdminRepository implements IAdminRepository {

    private DataBaseManager db;
    private AdminRepository adminRepository;

    public AdminRepository getAdminRepository() {
        if (adminRepository != null)
            return adminRepository;
        adminRepository = new AdminRepository();
        return adminRepository;

    }

    private AdminRepository() {
        db = DataBaseManager.getInstance();
    }

    @Override
    public Admin findById(UUID id) throws SQLException {
        String query = "select * from admins where id = ?";
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error de busqueda"));
        if (result.next()) {

            Admin admin = null;
            try {
                admin = new Admin(
                        UUID.fromString(result.getString("id")),
                        result.getString("name"),
                        result.getString("email"),
                        result.getString("password"),
                        new SimpleDateFormat("dd/MM/yy").parse(result.getString("createDate")),
                        new SimpleDateFormat("dd/MM/yy").parse(result.getString("birthDate"))
                );
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            db.close();
            return admin;
        }
        db.close();
        return null;
    }

    @Override
    public List<Admin> findAll() throws SQLException {
        String sql = "select * from admins";
        db.open();
        ResultSet result = db.select(sql).orElseThrow(() -> new SQLException("Error al obtener todos las personas"));
        ArrayList<Admin> lista = new ArrayList<>();
        while (result.next()) {
            // Lo llevamos a memoria
            try {
                lista.add(
                        new Admin(
                                UUID.fromString(result.getString("id")),
                                result.getString("name"),
                                result.getString("email"),
                                result.getString("password"),
                                new SimpleDateFormat("dd/MM/yy").parse(result.getString("createDate")),
                                new SimpleDateFormat("dd/MM/yy").parse(result.getString("birthDate"))
                        )

                );
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        db.close();
        return lista;
    }

    @Override
    public Admin update(Admin item) throws SQLException {
        String query = "UPDATE admins SET" +
                "id = ?" +
                "name = ?," +

                "email = ?," +
                "password = ?," +
                "createDate = ?," +
                "birthDate =?" +
                "where id = ?";
        db.open();

        db.update(
                query,
                item.id.toString(),
                item.name,
                item.email,
                item.password,
                item.createDate,
                item.birthDate,
                item.id
        );
        db.close();
        return item;
    }

    @Override
    public Admin add(Admin item) throws SQLException {
        String query = "INSERT into admins (id, name, email, password, createDate, birthDate)" +
                "values (?,?,?,?,?,?)";
        db.open();
        db.insert(
                query,
                item.id.toString(),
                item.name,
                item.email,
                item.password,
                item.createDate,
                item.birthDate
        ).orElseThrow(() -> new SQLException("No se pudo insertar el nuevo admin"));
        db.close();
        return item;
    }

    @Override
    public void delete(UUID id) throws SQLException {
        String query = "delete from admins where id = ?";
        db.open();
        db.delete(query, id);
        db.close();
    }
}
