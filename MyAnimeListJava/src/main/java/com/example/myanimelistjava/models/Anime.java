package com.example.myanimelistjava.models;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class Anime {
    private String title;
    private String titleEnglish;
    private String types;
    private int episodes;
    private String status;
    private Date date;
    private String rating;
    private List<String> genres;
    private String img;
    private final UUID id= UUID.randomUUID();

    public Anime(String title, String title_english, String type, int episodes, String status, java.sql.Date releaseDate, String rating, List<String> genre, String imageUrl, UUID id) {
    }
}
