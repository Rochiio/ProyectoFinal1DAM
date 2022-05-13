package com.example.myanimelist.controllers;

import java.awt.Desktop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AcercaDeController {
    private Desktop desktop=Desktop.getDesktop();

    @FXML
    private void linkGitHubRo(ActionEvent event) {
        try {
            desktop.browse(new URI("https://github.com/Rochiio"));
        } catch (IOException | URISyntaxException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al abrir la página");
            alert.setContentText(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void linkGitHubMo(ActionEvent event) {
            try {
                desktop.browse(new URI("https://github.com/loopedmoha"));
            } catch (IOException | URISyntaxException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error al abrir la página");
                alert.setContentText(e.getMessage());
                e.printStackTrace();
            }
        }

    @FXML
    private void linkGitHubJo(ActionEvent event) {
        try {
            desktop.browse(new URI("https://github.com/JoaquinAyG"));
        } catch (IOException | URISyntaxException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al abrir la página");
            alert.setContentText(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void linkGitHubRob(ActionEvent event) {
        try {
            desktop.browse(new URI("https://github.com/xBaank"));
        } catch (IOException | URISyntaxException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al abrir la página");
            alert.setContentText(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void linkGitHubFr(ActionEvent event) {
        try {
            desktop.browse(new URI("https://github.com/frantoribio"));
        } catch (IOException | URISyntaxException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al abrir la página");
            alert.setContentText(e.getMessage());
            e.printStackTrace();
        }
    }

}
