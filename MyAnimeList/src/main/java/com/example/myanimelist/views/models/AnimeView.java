package com.example.myanimelist.views.models;

import com.example.myanimelist.models.Anime;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class AnimeView {
    final StringProperty title;
    final StringProperty titleEnglish;
    final StringProperty types;
    final IntegerProperty episodes;
    final StringProperty status;
    final ObjectProperty<LocalDate> date;
    final StringProperty rating;
    final StringProperty genres;
    final StringProperty img;
    final UUID id;

    public AnimeView(String title, String titleEnglish, String types, int episodes, String status, LocalDate date, String rating, List<String> genres, String img, UUID id) {
        this.title = new SimpleStringProperty(title);
        this.titleEnglish = new SimpleStringProperty(titleEnglish);
        this.types = new SimpleStringProperty(types);
        this.episodes = new SimpleIntegerProperty(episodes);
        this.status = new SimpleStringProperty(status);
        this.date = new SimpleObjectProperty<>(date);
        this.rating = new SimpleStringProperty(rating);
        this.genres = new SimpleStringProperty(String.join(",", genres));
        this.img = new SimpleStringProperty(img);
        this.id = id;
    }

    public AnimeView(Anime anime) {
        this.title = new SimpleStringProperty(anime.getTitle());
        this.titleEnglish = new SimpleStringProperty(anime.getTitleEnglish());
        this.types = new SimpleStringProperty(anime.getTypes());
        this.episodes = new SimpleIntegerProperty(anime.getEpisodes());
        this.status = new SimpleStringProperty(anime.getStatus());
        this.date = new SimpleObjectProperty<>(anime.getDate());
        this.rating = new SimpleStringProperty(anime.getRating());
        this.genres = new SimpleStringProperty(String.join(",", anime.getGenres()));
        this.img = new SimpleStringProperty(anime.getImg());
        this.id = anime.getId();
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

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
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
