package com.example.myanimelist.views.models;

import com.example.myanimelist.models.Review;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.UUID;

public class ReviewView {

    final UUID animeId;
    final UUID userId;
    final IntegerProperty score;
    final StringProperty comment;
    final UUID id;

    public ReviewView(int score, String comment, UUID id, UUID animeId, UUID userId) {
        this.id = id;
        this.score = new SimpleIntegerProperty(score);
        this.comment = new SimpleStringProperty(comment);
        this.animeId = animeId;
        this.userId = userId;
    }

    public ReviewView(Review review) {
        this.id = review.getId();
        this.score = new SimpleIntegerProperty(review.getScore());
        this.comment = new SimpleStringProperty(review.getComment());
        this.animeId = review.getAnime().getId();
        this.userId = review.getUser().getId();
    }

    public UUID getAnimeId() {
        return animeId;
    }

    public UUID getUserId() {
        return userId;
    }

    public int getScore() {
        return score.get();
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

    public String getComment() {
        return comment.get();
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public UUID getId() {
        return id;
    }
}
