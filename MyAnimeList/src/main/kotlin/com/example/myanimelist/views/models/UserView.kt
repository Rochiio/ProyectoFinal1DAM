package com.example.myanimelist.views.models

import com.example.myanimelist.models.User
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import java.time.LocalDate
import java.util.*

class UserView(user: User) {
    private val name: StringProperty
    private val email: StringProperty
    private val password: StringProperty
    private val createDate: ObjectProperty<LocalDate>
    private val birthDate: ObjectProperty<LocalDate>
    private val img: StringProperty
    var id: UUID

    init {
        id = user.id
        name = SimpleStringProperty(user.name)
        email = SimpleStringProperty(user.email)
        password = SimpleStringProperty(user.password)
        createDate = SimpleObjectProperty(user.createDate)
        birthDate = SimpleObjectProperty(user.birthDate)
        img = SimpleStringProperty(user.img)
    }

    fun toUser(): User {
        return User(getName(), getEmail(), getPassword(), getCreateDate(), getBirthDate(), getImg(), id)
    }

    fun getName(): String {
        return name.get()
    }

    fun setName(name: String) {
        this.name.set(name)
    }

    fun nameProperty(): StringProperty {
        return name
    }

    fun getEmail(): String {
        return email.get()
    }

    fun setEmail(email: String) {
        this.email.set(email)
    }

    fun emailProperty(): StringProperty {
        return email
    }

    fun getPassword(): String {
        return password.get()
    }

    fun setPassword(password: String) {
        this.password.set(password)
    }

    fun passwordProperty(): StringProperty {
        return password
    }

    fun getCreateDate(): LocalDate {
        return createDate.get()
    }

    fun setCreateDate(createDate: LocalDate) {
        this.createDate.set(createDate)
    }

    fun createDateProperty(): ObjectProperty<LocalDate> {
        return createDate
    }

    fun getBirthDate(): LocalDate {
        return birthDate.get()
    }

    fun setBirthDate(birthDate: LocalDate) {
        this.birthDate.set(birthDate)
    }

    fun birthDateProperty(): ObjectProperty<LocalDate> {
        return birthDate
    }

    fun getImg(): String {
        return img.get()
    }

    fun setImg(img: String) {
        this.img.set(img)
    }

    fun imgProperty(): StringProperty {
        return img
    }
}