package com.example.myanimelistjava;

import com.example.myanimelistjava.managers.DataBaseManager;
import com.example.myanimelistjava.managers.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

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
        checkDataBase();
        launch();
    }

    private static void checkDataBase() {
        System.out.println("Comprobamos la conexión al Servidor BD");
        DataBaseManager db = DataBaseManager.getInstance();
        try {
            db.open();
            Optional<ResultSet> rs = db.select("SELECT 'Hello world'");
            if (rs.isPresent()) {
                rs.get().next();
                db.close();
                System.out.println("Conexión correcta a la Base de Datos");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar al servidor de Base de Datos: " + e.getMessage());
            System.exit(1);
        }
    }
}