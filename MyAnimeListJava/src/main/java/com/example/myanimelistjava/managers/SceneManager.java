package com.example.myanimelistjava.managers;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Objects;

import static com.example.myanimelistjava.views.config.views.Views.MAIN;

public class SceneManager {
    private static SceneManager instance;
    private final Class<?> appClass;
    Logger logger = LogManager.getLogger(SceneManager.class);

    private Stage mainStage;

    private SceneManager(Class<?> appClass) {
        this.appClass = appClass;
        logger.info("SceneManager created");
        //System.out.println("SceneManager created");
    }

    public static SceneManager getInstance(Class<?> appClass) {
        if (instance == null) {
            instance = new SceneManager(appClass);
        }
        return instance;
    }

    public static SceneManager get() {
        return instance;
    }

    public void initMain() throws IOException {
        logger.info("Iniciando Main");
        Platform.setImplicitExit(true);

        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource(MAIN.get())));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(true);
    }
}
