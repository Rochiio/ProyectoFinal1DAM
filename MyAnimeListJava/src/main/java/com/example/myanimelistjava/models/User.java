package com.example.myanimelistjava.models;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class User {
    public final UUID id;
    public String name;
    public String email;
    public String password;
    public final Date createDate;
    public final Date birthDate;
}
