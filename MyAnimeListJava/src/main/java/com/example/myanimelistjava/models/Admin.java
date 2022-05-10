package com.example.myanimelistjava.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data

public class Admin extends AbstractUser{
    public Admin(UUID id, String name, String email, String password, String createDate, String birthDate){
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setBirthDate(LocalDate.parse(birthDate));
        this.setCreateDate(LocalDate.parse(createDate));
    }
}
