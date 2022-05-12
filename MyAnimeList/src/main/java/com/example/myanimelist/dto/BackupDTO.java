package com.example.myanimelist.dto;

import com.example.myanimelist.models.Admin;
import com.example.myanimelist.models.Anime;
import com.example.myanimelist.models.Review;
import com.example.myanimelist.models.User;
import lombok.Data;

/**
 * @author JoaquinAyG
 */
@Data
public class BackupDTO {
    public BackupDTO(Iterable<User> users, Iterable<Review> reviews, Iterable<Anime> animes, Iterable<Admin> admins) {
    }

}
