package com.example.myanimelist.dto;

import com.example.myanimelist.models.Admin;
import com.example.myanimelist.models.Anime;
import com.example.myanimelist.models.Review;
import com.example.myanimelist.models.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author JoaquinAyG
 */
@Data
@Builder
public class BackupDTO {

    List<User> users;
    List<Review> reviews;
    List<Anime> animes;
    List<Admin> admins;
}
