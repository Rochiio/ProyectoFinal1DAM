package com.example.myanimelist.repositories.admins

import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.Admin
import java.sql.SQLException
import java.util.*

//TODO use database.execute {} that auto opens and close connection with try-catch-finally
class AdminRepository(private val db: DataBaseManager) : IAdminRepository {
    /**
     * Busca en el repositorio un usuari de tipo admin usando su uuid
     * @param id UUID
     * @return Admin? El admin que se busca o null si no existe
     */
    override fun findById(id: UUID): Admin? {
        val query = "select * from usuarios where id = ?"
        val result = db.select(query, id)
        if (result.first()) {
            val admin = Admin(
                result.getString("nombre"),
                result.getString("email"),
                result.getString("password"),
                result.getDate("date_alta"),
                result.getDate("date_nacimiento"),
                UUID.fromString(result.getString("id"))
            )
            db.close()
            return admin
        }
        return null
    }

    /**
     * Recuepra de la base de datos una lisa con todos los admins
     * @return List<Admin?> Lista con todos los usuarios de tipo admin
     */
    override fun findAll(): List<Admin> {
        val query = "select * from usuarios"
        val admins = ArrayList<Admin>()
        db.open()
        val result = db.select(query)
        while (result.next()) {
            admins.add(
                Admin(
                    result.getString("nombre"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getDate("date_alta"),
                    result.getDate("date_nacimiento"),
                    UUID.fromString(result.getString("id"))
                )
            )
        }
        db.close()
        return admins
    }

    /**
     * Actualiza los datos de un admin
     * @param item Admin? Un admin con los datos que se quieren cambiar
     * @return Admin? Null si no se consigue. El nuevo admin si tiene exito
     */
    override fun update(item: Admin): Admin? {
        val query = "UPDATE usuarios SET" +
                "nombre = ?," +
                "date_alta = ?," +
                "password = ?," +
                "imageurl = 'NONE'," +
                "email = ?," +
                "date_nacimiento =?" +
                "where id = ?"
        db.open()
        val result = db.update(
            query, item!!.name,
            item.createDate,
            item.password,
            item.email,
            item.birthDate,
            item.id
        )
        db.close()
        return if (result == 0) null
        else item
    }

    /**
     * Añade un nuevo admin a la base de datos de usuarios
     * @param item Admin? El admin que se quiere añadir
     * @return Admin? El admin añadido o null si no se añadido ninguno
     * @throws SQLException En el caso de que ya existiese un admin con el mismo UUID o no se pueda acceder a la base de datos
     *
     */
    override fun add(item: Admin): Admin? {
        val query = "INSERT into Usuarios" +
                "values ?,?,?,?, 'NONE', ?,?"
        db.open()
        db.insert(
            query,
            item.id.toString(),
            item.name,
            item.createDate,
            item.password,
            item.email,
            item.birthDate
        )
        db.close()
        return item
    }

    /**
     * Elimina un admin de la base de datos usando su UUID
     * @param id UUID El uuid del admin que queremos eliminar
     * @return Admin? Deveulve el admin que ha sido eliminado
     * @throws SQLException en caso de que no exista un admin con el UUID dado o la base de datos no esté accesible
     */
    override fun delete(id: UUID) {
        val query = "delete from usuarios where id = ?"
        db.open()
        db.delete(query, id)
        db.close()
    }
}