package com.example.myanimelistjava.models;

import lombok.Data;

import java.util.UUID;

@Data
public class Review {
    public final UUID id;
    public final UUID anime;
    public final UUID user;
    public int score;
    public String comment;


    public Review(UUID id, UUID anime, UUID user, int score, String comment) {
        this.id= id;
        this.anime = anime;
        this.user = user;
        this.score = score;
        this.comment = comment;
    }
}
