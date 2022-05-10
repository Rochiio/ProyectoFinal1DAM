package com.example.myanimelistjava.models;

import java.util.Date;
import java.util.UUID;

public class Admin extends AbstractUser{
    public Admin(String name, String email, String password, Date createDate, Date birthDate) {
        super(name, email, password, createDate, birthDate);
    }

    public Admin(UUID id, String name, String email, String password, Date createDate, Date birthDate) {
        super(id, name, email, password, createDate, birthDate);
    }
}
