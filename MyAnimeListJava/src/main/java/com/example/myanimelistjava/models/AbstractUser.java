package com.example.myanimelistjava.models;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor


public abstract class AbstractUser {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private LocalDate createDate;
    private LocalDate birthDate;
}
