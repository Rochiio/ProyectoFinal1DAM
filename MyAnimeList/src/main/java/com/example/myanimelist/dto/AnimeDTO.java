package com.example.myanimelist.dto;

import com.example.myanimelist.models.Anime;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author JoaquinAyG
 */
@Data
@AllArgsConstructor
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
        this(anime.getId(), anime.getTitle(), anime.getTitleEnglish(), anime.getTypes(), anime.getEpisodes(), anime.getStatus(), anime.getDate().toString(), anime.getRating(), String.join(",", anime.getGenres()), anime.getImg());
    }

    public Anime fromDTO() {
        return new Anime(title, titleEnglish, types, episodes, status, LocalDate.parse(date), rating, Arrays.stream(genres.split(",")).toList(), img, id);
    }
}
