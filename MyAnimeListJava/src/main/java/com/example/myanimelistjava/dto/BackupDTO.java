package com.example.myanimelistjava.dto;

import com.example.myanimelistjava.models.Admin;
import com.example.myanimelistjava.models.Anime;
import com.example.myanimelistjava.models.Review;
import com.example.myanimelistjava.models.User;
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
