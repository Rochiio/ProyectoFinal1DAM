package com.example.myanimelist.dto;

import com.example.myanimelist.models.Anime;
import com.example.myanimelist.service.utils.Utils;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author JoaquinAyG
 */
public class AnimeDTO {
    public UUID id;
    public String title;
    public String titleEnglish;
    public String types;
    public Integer episodes;
    public String status;
    public String date;
    public String rating;
    public String genres;
    public String img;

    public AnimeDTO(Anime anime) {
        this(anime.getId(), anime.getTitle(), anime.getTitleEnglish(), anime.getTypes(), anime.getEpisodes(),
                anime.getStatus(), anime.getDate().toString(), anime.getRating(), String.join(",", anime.getGenres()), anime.getImg());
    }

    public AnimeDTO(UUID id, String title, String titleEnglish, String types, int episodes, String status,
                    String date, String rating, String genres, String img) {
        setId(id);
        setTitle(title);
        setTitleEnglish(titleEnglish);
        setTypes(types);
        setEpisodes(episodes);
        setStatus(status);
        setDate(date);
        setRating(rating);
        setGenres(genres);
        setImg(img);
    }

    public Anime fromDTO() {
        LocalDate newDate;
        try {
             newDate = Utils.parseLocalDate(this.date);
        } catch (DateTimeParseException e) {
            System.out.println("el puto roberto con el localdate");
            newDate = LocalDate.now();
        }
        return new Anime(title, titleEnglish, types, episodes, status, newDate, rating,
                Arrays.stream(genres.split(",")).toList(), img, id);
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
