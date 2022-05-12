package com.example.myanimelist.views.models;

import com.example.myanimelist.models.User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.UUID;

public class UserView {
    private final StringProperty name;
    private final StringProperty email;
    private final StringProperty password;
    private final ObjectProperty<LocalDate> createDate;
    private final ObjectProperty<LocalDate> birthDate;
    private final StringProperty img;
    private UUID id;

    public UserView(){
        this(null, null, null, null, null, null, null);
    }

    public UserView(String name, String email, String password, LocalDate createDate, LocalDate birthDate, String img, UUID id) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.createDate = new SimpleObjectProperty<>(createDate);
        this.birthDate = new SimpleObjectProperty<>(birthDate);
        this.img = new SimpleStringProperty(img);
    }

    public UserView(User user){
        this.id = user.getId();
        this.name = new SimpleStringProperty(user.getName());
        this.email = new SimpleStringProperty(user.getEmail());
        this.password = new SimpleStringProperty(user.getPassword());
        this.createDate = new SimpleObjectProperty<LocalDate>(user.getCreateDate());
        this.birthDate = new SimpleObjectProperty<LocalDate>(user.getBirthDate());
        this.img = new SimpleStringProperty(user.getImg());
    }

    public User toUser(){
        return new User(this.getName(), this.getEmail(), this.getPassword(), this.getCreateDate(), this.getBirthDate(), this.getImg(), this.getId());
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

    public LocalDate getCreateDate() {
        return createDate.get();
    }

    public ObjectProperty<LocalDate> createDateProperty() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate.set(createDate);
    }

    public LocalDate getBirthDate() {
        return birthDate.get();
    }

    public ObjectProperty<LocalDate> birthDateProperty() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate.set(birthDate);
    }

    public String getImg() {
        return img.get();
    }

    public StringProperty imgProperty() {
        return img;
    }

    public void setImg(String img) {
        this.img.set(img);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
