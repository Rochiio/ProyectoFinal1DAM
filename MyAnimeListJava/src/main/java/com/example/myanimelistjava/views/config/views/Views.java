package com.example.myanimelistjava.views.config.views;

public enum Views {
    SPLASH("views/splash-view.fxml"),
    MAIN("views/inicioSesion.fxml");


    private final String view;

    Views(String view) {
        this.view = view;
    }

    public String get() {
        return view;
    }
}
