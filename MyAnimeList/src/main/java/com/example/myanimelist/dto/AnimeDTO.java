package com.example.myanimelist.dto;

import com.example.myanimelist.models.Anime;


import java.sql.Date;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author JoaquinAyG
 */
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public void setTitleEnglish(String titleEnglish) {
        this.titleEnglish = titleEnglish;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
