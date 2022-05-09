package com.example.myanimelistjava.views.config;

enum viewConfig{
    WIDTH("600.0"),
    HEIGHT("400.0");
    private final String value;
    viewConfig(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}