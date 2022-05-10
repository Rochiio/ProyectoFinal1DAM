package com.example.myanimelistjava.models.enums;

public enum Genre {
    ADVENTURE("Adventure"),
    ACTION("Action"),
    COMEDY("Comedy"),
    SLICE_OF_LIFE("Slice of life"),
    DRAMA("Drama"),
    FANTASY("Fantasy"),
    SUPERNATURAL("Supernatural"),
    MAGIC("Magic"),
    MYSTERY("Mystery"),
    HORROR("Horror"),
    PSYCHOLOGICAL("Psychological"),
    SCI_FI("Sci-Fi"),
    ROMANCE("Romance");

    private final String value;

    Genre(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
