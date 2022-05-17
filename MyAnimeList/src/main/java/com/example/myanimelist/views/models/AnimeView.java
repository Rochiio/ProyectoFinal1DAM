package com.example.myanimelist.views.models;

import com.example.myanimelist.models.Anime;
import com.example.myanimelist.models.enums.Genre;
import com.example.myanimelist.models.enums.Status;
import com.example.myanimelist.models.enums.Type;
import javafx.beans.property.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AnimeView {

    IntegerProperty ranking;
    final ObjectProperty<Presentation> presentation;
    final StringProperty types;
    final IntegerProperty episodes;
    final StringProperty status;
    final ObjectProperty<LocalDate> date;
    final StringProperty rating;
    final StringProperty genres;
    final UUID id;

    public AnimeView(String title, String titleEnglish, String types, int episodes, String status, LocalDate date, String rating, List<String> genres, String img, UUID id) {
        this.presentation = new SimpleObjectProperty<>(new Presentation(title, titleEnglish, img));
        this.types = new SimpleStringProperty(types);
        this.episodes = new SimpleIntegerProperty(episodes);
        this.status = new SimpleStringProperty(status);
        this.date = new SimpleObjectProperty<>(date);
        this.rating = new SimpleStringProperty(rating);
        this.genres = new SimpleStringProperty(String.join(",", genres));
        this.id = id;
    }

    public AnimeView(Anime anime) {
        this.presentation = new SimpleObjectProperty<>(new Presentation(anime.getTitle(), anime.getTitleEnglish(), anime.getImg()));
        this.types = new SimpleStringProperty(anime.getTypes());
        this.episodes = new SimpleIntegerProperty(anime.getEpisodes());
        this.status = new SimpleStringProperty(anime.getStatus());
        this.date = new SimpleObjectProperty<>(anime.getDate());
        this.rating = new SimpleStringProperty(anime.getRating());
        this.genres = new SimpleStringProperty(String.join(",", anime.getGenres()));
        this.id = anime.getId();
    }

    public Anime toPOJO(){
        return new Anime(this.presentation.get().getTitle(),
                this.presentation.get().getTitleEnglish(),
                this.getTypes(),
                this.getEpisodes(),
                this.getStatus(),
                this.getDate(),
                this.getRating(),
                Arrays.stream(this.getGenres().split(",")).toList(),
                this.presentation.get().getImg(),
                this.getId()
                );
    }

    public int getRanking() {
        return ranking.get();
    }

    public IntegerProperty rankingProperty() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking.set(ranking);
    }

    public Presentation getPresentation() {
        return presentation.get();
    }

    public ObjectProperty<Presentation> presentationProperty() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation.set(presentation);
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


    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "AnimeView{" +
                "title=" + presentation.get().getTitle() +
                ", titleEnglish=" + presentation.get().getTitleEnglish() +
                ", types=" + types +
                ", episodes=" + episodes +
                ", status=" + status +
                ", date=" + date +
                ", rating=" + rating +
                ", genres=" + genres +
                ", img=" + presentation.get().getImg() +
                ", id=" + id +
                '}';
    }

    public void enumParser(String selection) {
        for(String sample :Genre.Companion.getSample()){
            if (Objects.equals(selection, sample.split(",")[0])) setGenres(selection);
            return;
        }

        for(String sample : Status.Companion.getSample()){
            if (Objects.equals(selection, sample)) setStatus(selection);
            return;
        }

        for(String sample : Type.Companion.getSample()){
            if (Objects.equals(selection, sample)) setTypes(selection);
        }
    }
}
