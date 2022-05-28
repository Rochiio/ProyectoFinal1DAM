package com.example.myanimelist.dto

import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.Review
import com.example.myanimelist.models.User

/**
 * @author JoaquinAyG
 */
class BackupDTO(var users: ArrayList<User>, var reviews: ArrayList<Review>, var animes: ArrayList<Anime>)