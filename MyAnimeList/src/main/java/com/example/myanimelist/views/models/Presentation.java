package com.example.myanimelist.views.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Presentation {

    final StringProperty title;
    final StringProperty titleEnglish;
    final StringProperty img;

    public Presentation(String title, String titleEnglish, String img) {
        this.img = new SimpleStringProperty(img);
        this.title = new SimpleStringProperty(title);
        this.titleEnglish = new SimpleStringProperty(titleEnglish);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getTitleEnglish() {
        return titleEnglish.get();
    }

    public void setTitleEnglish(String titleEnglish) {
        this.titleEnglish.set(titleEnglish);
    }

    public StringProperty titleEnglishProperty() {
        return titleEnglish;
    }

    public String getImg() {
        return img.get();
    }

    public void setImg(String img) {
        this.img.set(img);
    }

    public StringProperty imgProperty() {
        return img;
    }
}
