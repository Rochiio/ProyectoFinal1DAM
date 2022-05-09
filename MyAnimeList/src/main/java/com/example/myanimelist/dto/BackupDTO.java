package com.example.myanimelist.dto;

import com.example.myanimelist.models.Admin;
import com.example.myanimelist.models.Anime;
import com.example.myanimelist.models.Reviews;
import com.example.myanimelist.models.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BackupDTO {

    List<User> users;
    List<Reviews> reviews;
    List<Anime> animes;
    List<Admin> admins;
}
