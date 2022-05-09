package com.example.myanimelist.dto;

import com.example.myanimelist.models.Anime;
import lombok.Data;

import java.sql.Date;
import java.util.Arrays;
import java.util.UUID;

@Data
public class AnimeDTO {
    UUID id;
    String title;
    String titleEnglish;
    String types;
    Integer episodes;
    String status;
    String date;
    String rating;
    String genres;
    String img;

    public AnimeDTO(Anime anime) {
        this.id = anime.getId();
        this.title = anime.getTitle();
        this.titleEnglish = anime.getTitleEnglish();
        this.types = anime.getTypes();
        this.episodes = anime.getEpisodes();
        this.status = anime.getStatus();
        this.date = anime.getDate().toString();
        this.rating = anime.getRating();
        this.genres = String.join(",", anime.getGenres());
        this.img = anime.getImg();
    }

    public AnimeDTO(UUID id, String title, String titleEnglish, String types, Integer episodes, String status, String date, String rating, String genres, String img) {
        this.id = id;
        this.title = title;
        this.titleEnglish = titleEnglish;
        this.types = types;
        this.episodes = episodes;
        this.status = status;
        this.date = date;
        this.rating = rating;
        this.genres = genres;
        this.img = img;
    }

    public Anime fromDTO() {
        return new Anime(title, titleEnglish, types, episodes, status, Date.valueOf(date), rating, Arrays.stream(genres.split(",")).toList(), img, id);
    }
}
