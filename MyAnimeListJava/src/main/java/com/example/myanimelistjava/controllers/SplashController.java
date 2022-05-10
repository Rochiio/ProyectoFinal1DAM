package com.example.myanimelistjava.controllers;

import com.example.myanimelistjava.managers.SceneManager;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.val;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class SplashController implements Initializable {
    Logger logger = LogManager.getLogger(SplashController.class);
    @FXML
    private ImageView fondo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            fondo.setImage( new Image(new FileInputStream(randomImg())));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Una animación de fondo
        FadeTransition transition = new FadeTransition(Duration.millis(5000), fondo);
        transition.setFromValue(1.0);
        transition.setToValue(1.0);
        transition.play();

        transition.setOnFinished(event -> {
            // Nos cerramos
            Stage ventana = (Stage) fondo.getScene().getWindow();
            ventana.hide();
            // Mostramos la ventana principal
            SceneManager sceneManager = SceneManager.get();
            try {
                sceneManager.initMain();
            } catch (IOException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        });
    }


    /**
     * Elegir imagen de splash aleatoria
     * @return imagen aleatoria
     */
    private String randomImg(){
        int rNum = (int) Math.random()*4+1;
        return "src/main/resources/com/example/myanimelist/images/splash/splash2.png";
    }


}
