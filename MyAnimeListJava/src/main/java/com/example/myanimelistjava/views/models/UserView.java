package com.example.myanimelistjava.views.models;

import javafx.beans.property.StringProperty;

import java.util.UUID;

public class UserView {

    private final StringProperty name;
    private final StringProperty email;
    private final StringProperty password;
    private final StringProperty createDate;
    private final StringProperty birthDate;
    private final UUID id;

    public UserView(StringProperty name, StringProperty email, StringProperty password, StringProperty createDate, StringProperty birthDate, UUID id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.createDate = createDate;
        this.birthDate = birthDate;
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getCreateDate() {
        return createDate.get();
    }

    public StringProperty createDateProperty() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate.set(createDate);
    }

    public String getBirthDate() {
        return birthDate.get();
    }

    public StringProperty birthDateProperty() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate.set(birthDate);
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "UserView{" +
                "name=" + name +
                ", email=" + email +
                ", password=" + password +
                ", createDate=" + createDate +
                ", birthDate=" + birthDate +
                ", id=" + id +
                '}';
    }
}
