package com.example.myanimelistjava.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Data

public class User extends AbstractUser{

    private String img;
    private ArrayList<Anime> mylist;

    public User(){
        super();
        this.img = "default.png";
        this.mylist = new ArrayList<Anime>();

    }


}
