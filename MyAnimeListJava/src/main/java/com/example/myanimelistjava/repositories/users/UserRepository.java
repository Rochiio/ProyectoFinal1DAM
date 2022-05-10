package com.example.myanimelistjava.repositories.users;

import com.example.myanimelistjava.managers.DataBaseManager;
import com.example.myanimelistjava.models.Admin;
import com.example.myanimelistjava.models.User;
import lombok.val;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class UserRepository implements IUserRepository {

    private DataBaseManager db;
    private UserRepository userRepository;

    public UserRepository getUserRepository() {
        if (userRepository != null)
            return userRepository;
        userRepository = new UserRepository();
        return userRepository;

    }

    private UserRepository() {
        db = DataBaseManager.getInstance();
    }

    @Override
    public User findById(UUID id) throws SQLException {
        User user = null;

        val set = db.select("SELECT * FROM Usuarios WHERE id = ?", id.toString()).get();
        db.open();
           /* if (!set.next()) return null;

            user = new User(
                    UUID.fromString(set.getString("id")),
                    set.getString("nombre"),
                    set.getString("email"),
                    set.getString("password"),
                    set.getDate("date_alta"),
                    set.getDate("date_nacimiento"),
                    set.getString("imageUrl")
            );*/
        db.close();
        return user;
    }

    @Override
    public List<User> findAll() throws SQLException {
        String sql = "select * from usuarios";
        db.open();
        ResultSet result = db.select(sql).orElseThrow(() -> new SQLException("Error al obtener todos las personas"));
        ArrayList<User> lista = new ArrayList<>();
        while (result.next()) {
            // Lo llevamos a memoria
            try {
                lista.add(
                        new User(
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
    public User update(User item) throws SQLException {
        String query = "UPDATE usuarios SET" +
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
    public User add(User item) throws SQLException {
        String query = "INSERT into usuarios (id, name, email, password, createDate, birthDate)" +
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
        ).orElseThrow(() -> new SQLException("No se pudo insertar el nuevo usuario"));
        db.close();
        return item;
    }

    @Override
    public void delete(UUID id) throws SQLException {
        String query = "delete from usuarios where id = ?";
        db.open();
        db.delete(query, id);
        db.close();
    }
}
