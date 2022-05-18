package com.example.myanimelist.dto;

import com.example.myanimelist.models.Admin;
import com.example.myanimelist.models.Anime;
import com.example.myanimelist.models.Review;
import com.example.myanimelist.models.User;

import java.util.ArrayList;

/**
 * @author JoaquinAyG
 */

public class BackupDTO {
    public BackupDTO(ArrayList<User> users, ArrayList<Review> reviews, ArrayList<Anime> animes, ArrayList<Admin> admins) {
        this.users = users;
        this.reviews = reviews;
        this.animes = animes;
        this.admins = admins;
    }

    public BackupDTO() {
    }

    public ArrayList<User> users;
    public ArrayList<Review> reviews;
    public ArrayList<Anime> animes;
    public ArrayList<Admin> admins;
}
