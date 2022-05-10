package com.example.myanimelistjava.models.enums;

public enum Status {
    FINISHED_AIRING("Finished Airing"),
    CURRENTLY_AIRING("Currently Airing"),
    NOT_YET_AIRED("Not yet aired");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
