package com.example.myanimelistjava.views.config;

public enum ViewConfig {
    WIDTH("600.0"),
    HEIGHT("400.0");
    private final String value;
    ViewConfig(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}