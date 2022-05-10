package com.example.myanimelistjava.models;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class Anime {
    public String title;
    public String titleEnglish;
    public String types;
    public int episodes;
    public String status;
    public Date date;
    public String rating;
    public List<String> genres;
    public String img;
    public final UUID id= UUID.randomUUID();

    public Anime(String title, String title_english, String type, int episodes, String status, java.sql.Date releaseDate, String rating, List<String> genre, String imageUrl, UUID id) {
    }
}