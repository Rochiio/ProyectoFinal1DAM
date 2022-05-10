package com.example.myanimelistjava;

import com.example.myanimelistjava.managers.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MyAnimeListApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        var sceneManager = SceneManager.getInstance(MyAnimeListApplication.class);
        try {
            sceneManager.initSplash(stage);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}