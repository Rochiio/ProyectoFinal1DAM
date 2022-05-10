package com.example.myanimelistjava.views.models;

import javafx.beans.property.*;
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
    private final ObjectProperty<List<String>> genres;
    private final StringProperty img;
    private final UUID id;

    public AnimeView(String title, String titleEnglish, String types, int episodes, String status, String date, String rating, List<String> genres, String img, UUID id) {
        this.title = new SimpleStringProperty(title);
        this.titleEnglish = new SimpleStringProperty(titleEnglish);
        this.types = new SimpleStringProperty(types);
        this.episodes = new SimpleIntegerProperty(episodes);
        this.status = new SimpleStringProperty(status);
        this.date = new SimpleStringProperty(date);
        this.rating = new SimpleStringProperty(rating);
        this.genres = new SimpleObjectProperty<>(genres);
        this.img = new SimpleStringProperty(img);
        this.id = id;
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

    public List<String> getGenres() {
        return genres.get();
    }

    public ObjectProperty<List<String>> genresProperty() {
        return genres;
    }

    public void setGenres(List<String> genres) {
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
