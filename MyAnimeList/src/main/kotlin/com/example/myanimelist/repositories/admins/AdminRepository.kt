package com.example.myanimelist.repositories.admins

import com.example.myanimelist.extensions.execute
import com.example.myanimelistjava.managers.DataBaseManager
import com.example.myanimelist.models.Admin
import java.sql.SQLException
import java.util.*

class AdminRepository(private val db: _root_ide_package_.com.example.myanimelistjava.managers.DataBaseManager) : IAdminRepository {
    /**
     * Busca en el repositorio un usuari de tipo admin usando su uuid
     * @param id UUID
     * @return Admin? El admin que se busca o null si no existe
     */
    override fun findById(id: UUID): Admin? {
        val query = "select * from admins where id = ?"
        db.execute {

            val result = db.select(query, id)
            if (result.next()) {
                val admin = Admin(
                    result.getString("name"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getDate("createDate"),
                    result.getDate("birthDate"),
                    UUID.fromString(result.getString("id"))
                )

                return admin
            }
        }
        return null
    }

    /**
     * Recuepra de la base de datos una lisa con todos los admins
     * @return List<Admin?> Lista con todos los usuarios de tipo admin
     */
    override fun findAll(): List<Admin> {
        val query = "select * from admins"
        val admins: MutableList<Admin> = mutableListOf()
        db.execute {

            val result = db.select(query)
            while (result.next()) {
                admins.add(
                    Admin(
                        result.getString("name"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getDate("createDate"),
                        result.getDate("birthDate"),
                        UUID.fromString(result.getString("id"))
                    )
                )
            }

        }

        return admins
    }

    /**
     * Actualiza los datos de un admin
     * @param item Admin? Un admin con los datos que se quieren cambiar
     * @return Admin? Null si no se consigue. El nuevo admin si tiene exito
     */
    override fun update(item: Admin): Admin? {
        val query = "UPDATE admins SET" +
                "id = ?" +
                "name = ?," +

                "email = ?," +
                "password = ?," +
                "createDate = ?," +
                "birthDate =?" +
                "where id = ?"
        db.execute {

            db.update(
                query,
                item.id.toString(),
                item.name,
                item.email,
                item.password,
                item.createDate,
                item.birthDate,
                item.id
            )

            return item
        }
        return null
    }

    /**
     * Añade un nuevo admin a la base de datos de usuarios
     * @param item Admin? El admin que se quiere añadir
     * @return Admin? El admin añadido o null si no se añadido ninguno
     * @throws SQLException En el caso de que ya existiese un admin con el mismo UUID o no se pueda acceder a la base de datos
     *
     */
    override fun add(item: Admin): Admin? {
        val query = "INSERT into admins (id, name, email, password, createDate, birthDate)" +
                "values (?,?,?,?,?,?)"
        db.execute {
            db.insert(
                query,
                item.id.toString(),
                item.name,
                item.email,
                item.password,
                item.createDate,
                item.birthDate
            )
            return item
        }


        return null

    }

    /**
     * Elimina un admin de la base de datos usando su UUID
     * @param id UUID El uuid del admin que queremos eliminar
     * @return Admin? Deveulve el admin que ha sido eliminado
     * @throws SQLException en caso de que no exista un admin con el UUID dado o la base de datos no esté accesible
     */
    override fun delete(id: UUID) {
        val query = "delete from admins where id = ?"
        db.execute {
            db.delete(query, id)
        }

    }
}