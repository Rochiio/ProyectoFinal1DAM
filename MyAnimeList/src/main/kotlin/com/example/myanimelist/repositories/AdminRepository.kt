package com.example.myanimelist.repositories

import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.*
import java.util.*

class AdminRepository(val db: DataBaseManager) : IAdminRepository {
    override fun findbyId(id: UUID): Admin? {
        var query = "select * from usuarios where id = ?"
        val result = db.select(query, id).get()
        if (result.first()) {
            val admin = Admin(
                UUID.fromString(result.getString("id")),
                result.getString("nombre"),
                result.getString("email"),
                result.getString("password"),
                result.getDate("date_alta"),
                result.getDate("date_nacimiento")
            )
            db.close()
            return admin
        }
        return null
    }

    override fun findAll(): List<Admin> {
        var query = "select * from usuarios"
        val result = db.select(query, id)
        while(result.next()){

        }
        if (result.first()) {
            val admin = Admin(
                UUID.fromString(result.getString("id")),
                result.getString("nombre"),
                result.getString("email"),
                result.getString("password"),
                result.getDate("date_alta"),
                result.getDate("date_nacimiento")
            )
            db.close()
            return admin
        }
        return null
    }

    override fun update(item: Admin?): Admin? {
        TODO("Not yet implemented")
    }

    override fun add(item: Admin?): Admin? {
        TODO("Not yet implemented")
    }


    override fun delete(id: UUID): Admin {
        TODO("Not yet implemented")
    }
}