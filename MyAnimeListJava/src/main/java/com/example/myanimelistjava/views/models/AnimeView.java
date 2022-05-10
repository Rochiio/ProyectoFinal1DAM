package com.example.myanimelistjava.views.models;

import com.example.myanimelistjava.models.Anime;
import javafx.beans.property.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class AnimeView {
    private final StringProperty title;
    private final StringProperty titleEnglish;
    private final StringProperty types;
    private final IntegerProperty episodes;
    private final StringProperty status;
    private final StringProperty date;
    private final StringProperty rating;
    private final StringProperty genres;
    private final StringProperty img;
    private final UUID id;

    public AnimeView(String title, String titleEnglish, String types, int episodes, String status, String date, String rating, String genres, String img, UUID id) {
        this.title = new SimpleStringProperty(title);
        this.titleEnglish = new SimpleStringProperty(titleEnglish);
        this.types = new SimpleStringProperty(types);
        this.episodes = new SimpleIntegerProperty(episodes);
        this.status = new SimpleStringProperty(status);
        this.date = new SimpleStringProperty(date);
        this.rating = new SimpleStringProperty(rating);
        this.genres = new SimpleStringProperty(genres);
        this.img = new SimpleStringProperty(img);
        this.id = id;
    }

    public AnimeView(Anime anime) {
        this.title = new SimpleStringProperty(anime.getTitle());
        this.titleEnglish = new SimpleStringProperty(anime.getTitleEnglish());
        this.types = new SimpleStringProperty(anime.getTypes());
        this.episodes = new SimpleIntegerProperty(anime.getEpisodes());
        this.status = new SimpleStringProperty(anime.getStatus());
        this.date = new SimpleStringProperty(anime.getDate().toString());
        this.rating = new SimpleStringProperty(anime.getRating());
        this.genres = new SimpleStringProperty(String.join(",",anime.getGenres()));
        this.img = new SimpleStringProperty(anime.getImg());
        this.id = anime.getId();
    }

    public Anime toPojo(){
        return new Anime(title.get(), titleEnglish.get(), types.get(), episodes.get(), status.get(), Date.valueOf(date.get()), rating.get(), Arrays.stream(genres.get().split(",")).toList(), img.get(), id);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getTitleEnglish() {
        return titleEnglish.get();
    }

    public StringProperty titleEnglishProperty() {
        return titleEnglish;
    }

    public void setTitleEnglish(String titleEnglish) {
        this.titleEnglish.set(titleEnglish);
    }

    public String getTypes() {
        return types.get();
    }

    public StringProperty typesProperty() {
        return types;
    }

    public void setTypes(String types) {
        this.types.set(types);
    }

    public int getEpisodes() {
        return episodes.get();
    }

    public IntegerProperty episodesProperty() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes.set(episodes);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getRating() {
        return rating.get();
    }

    public StringProperty ratingProperty() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating.set(rating);
    }

    public String getGenres() {
        return genres.get();
    }

    public StringProperty genresProperty() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres.set(genres);
    }

    public String getImg() {
        return img.get();
    }

    public StringProperty imgProperty() {
        return img;
    }

    public void setImg(String img) {
        this.img.set(img);
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "AnimeView{" +
                "title=" + title +
                ", titleEnglish=" + titleEnglish +
                ", types=" + types +
                ", episodes=" + episodes +
                ", status=" + status +
                ", date=" + date +
                ", rating=" + rating +
                ", genres=" + genres +
                ", img=" + img +
                ", id=" + id +
                '}';
    }
}
