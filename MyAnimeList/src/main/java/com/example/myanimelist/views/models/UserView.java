package com.example.myanimelist.views.models;

import com.example.myanimelist.models.User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;
import java.util.UUID;

public class UserView {
    private final StringProperty name;
    private final StringProperty email;
    private final StringProperty password;
    private final ObjectProperty<Date> createDate;
    private final ObjectProperty<Date> birthDate;
    private final StringProperty img;
    private UUID id;

    public UserView() {
        this(null, null, null, null, null, null, null);
    }

    public UserView(String name, String email, String password, Date createDate, Date birthDate, String img, UUID id) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.createDate = new SimpleObjectProperty<>(createDate);
        this.birthDate = new SimpleObjectProperty<>(birthDate);
        this.img = new SimpleStringProperty(img);
    }

    public UserView(User user) {
        this.id = user.getId();
        this.name = new SimpleStringProperty(user.getName());
        this.email = new SimpleStringProperty(user.getEmail());
        this.password = new SimpleStringProperty(user.getPassword());
        this.createDate = new SimpleObjectProperty<Date>(user.getCreateDate());
        this.birthDate = new SimpleObjectProperty<Date>(user.getBirthDate());
        this.img = new SimpleStringProperty(user.getImg());
    }

    public User toUser() {
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

    public Date getCreateDate() {
        return createDate.get();
    }

    public ObjectProperty<Date> createDateProperty() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate.set(createDate);
    }

    public Date getBirthDate() {
        return birthDate.get();
    }

    public ObjectProperty<Date> birthDateProperty() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
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
