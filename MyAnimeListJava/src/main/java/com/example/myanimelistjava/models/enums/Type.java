package com.example.myanimelistjava.models.enums;

public enum Type {
    TV("TV"),
    MOVIE("Movie"),
    MUSIC("Music"),
    OVA("OVA"),
    ONA("ONA"),
    SPECIAL("Special");

    private final String value;

    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
