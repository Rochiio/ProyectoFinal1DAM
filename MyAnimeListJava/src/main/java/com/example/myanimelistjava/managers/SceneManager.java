package com.example.myanimelistjava.managers;

import com.example.myanimelistjava.MyAnimeListApplication;
import com.example.myanimelistjava.configurations.ViewConfig;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Objects;

import static com.example.myanimelistjava.views.Views.MAIN;
public class SceneManager {
    private static SceneManager instance;
    private final Class<?> appClass;
    Logger logger = LogManager.getLogger(SceneManager.class);

    private Stage mainStage;

    private SceneManager(Class<?> appClass) {
        this.appClass = appClass;
        logger.info("SceneManager created");
    }

    public static SceneManager getInstance(Class<?> appClass) {
        if (instance == null) {
            instance = new SceneManager(appClass);
        }
        return instance;
    }

    public void initSplash(Stage stage) throws IOException, InterruptedException {
        Platform.setImplicitExit(false);
        logger.info("Iniciando Splash");
        FXMLLoader fxmlLoader = new FXMLLoader(MyAnimeListApplication.class.getResource("views/splash-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),Double.parseDouble(ViewConfig.WIDTH.getValue()), Double.parseDouble(ViewConfig.HEIGHT.getValue()));
        stage.setTitle("Programa");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public static SceneManager get() {
        return instance;
    }

    public void initMain() throws IOException {
        logger.info("Iniciando Main");
        Platform.setImplicitExit(true);
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(appClass.getResource("views/inicioSesion-view.fxml")));
        Scene scene = new Scene(fxmlLoader.load(), 600.0, 450.0);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Log In");
        logger.info("Scene loaded");
        stage.setScene(scene);
        mainStage = stage;
        stage.show();
    }
}
