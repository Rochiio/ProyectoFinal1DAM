package com.example.myanimelist.dto;

import com.example.myanimelist.models.Admin;
import com.example.myanimelist.models.Anime;
import com.example.myanimelist.models.Review;
import com.example.myanimelist.models.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @author JoaquinAyG
 */
@AllArgsConstructor
@NoArgsConstructor
public class BackupDTO {
    public ArrayList<User> users;
    public ArrayList<Review> reviews;
    public ArrayList<Anime> animes;
    public ArrayList<Admin> admins;
}
