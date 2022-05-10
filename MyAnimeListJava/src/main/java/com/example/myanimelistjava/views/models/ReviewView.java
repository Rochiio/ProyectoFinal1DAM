package com.example.myanimelistjava.views.models;

import com.example.myanimelistjava.models.Anime;
import com.example.myanimelistjava.models.User;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

import java.util.UUID;

public class ReviewView {

    private final ObjectProperty<Anime> anime;
    private final ObjectProperty<User> user;
    private final IntegerProperty score;
    private final StringProperty comment;
    private final UUID id;

    public ReviewView(ObjectProperty<Anime> anime, ObjectProperty<User> user, IntegerProperty score, StringProperty comment, UUID id) {
        this.anime = anime;
        this.user = user;
        this.score = score;
        this.comment = comment;
        this.id = id;
    }

    public Anime getAnime() {
        return anime.get();
    }

    public ObjectProperty<Anime> animeProperty() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime.set(anime);
    }

    public User getUser() {
        return user.get();
    }

    public ObjectProperty<User> userProperty() {
        return user;
    }

    public void setUser(User user) {
        this.user.set(user);
    }

    public int getScore() {
        return score.get();
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ReviewView{" +
                "anime=" + anime +
                ", user=" + user +
                ", score=" + score +
                ", comment=" + comment +
                ", id=" + id +
                '}';
    }
}
