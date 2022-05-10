package com.example.myanimelistjava.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class User extends AbstractUser {

    List<Anime> myList;
    String img;

    public User(String name, String email, String password, Date createDate, Date birthDate) {
        super(name, email, password, createDate, birthDate);
        this.myList = new ArrayList<>();
        this.img = "";
    }

    public User(UUID id, String name, String email, String password, Date createDate, Date birthDate, List<Anime> myList, String img) {
        super(id, name, email, password, createDate, birthDate);
        this.myList = myList;
        this.img = img;
    }

    public User(UUID id, String name, String email, String password, Date createDate, Date birthDate) {
        super(id, name, email, password, createDate, birthDate);
        this.myList = null;

    }

    public List<Anime> getMyList() {
        return myList;
    }

    public void setMyList(List<Anime> myList) {
        this.myList = myList;
    }

    public void addToMyList(Anime anime){
        this.myList.add(anime);
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
