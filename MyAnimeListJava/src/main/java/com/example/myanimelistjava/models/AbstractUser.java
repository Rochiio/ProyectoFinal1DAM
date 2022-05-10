package com.example.myanimelistjava.models;

import java.util.Date;
import java.util.UUID;

public abstract class AbstractUser {
    public final UUID id;
    public String name;
    public String email;
    public String password;
    public final Date createDate;
    public final Date birthDate;

    public AbstractUser(String name, String email, String password, Date createDate, Date birthDate) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.createDate = createDate;
        this.birthDate = birthDate;
    }

    public AbstractUser(UUID id, String name, String email, String password, Date createDate, Date birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createDate = createDate;
        this.birthDate = birthDate;
    }
}
