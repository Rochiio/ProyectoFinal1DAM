package com.example.myanimelist.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import java.io.IOException;

public class AcercaDeController {

    @FXML
    public Hyperlink gitHubLinkRo;
    @FXML
    public Hyperlink gitHubLinkMo;
    public Hyperlink gitHubLinkJo;
    @FXML
    public Hyperlink gitHubLinkRob;
    @FXML
    public Hyperlink gitHubLinkFr;
    @FXML
    public Hyperlink gitHubOpenProject;




    @FXML
    private void linkGitHubRo(ActionEvent event) {
        try {
            new ProcessBuilder("x-www-browser", "https://github.com/Rochiio").start();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al abrir la página");
            alert.setContentText(e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    private void linkGitHubMo(ActionEvent event) {
        try {
            new ProcessBuilder("x-www-browser", "https://github.com/loopedmoha").start();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al abrir la página");
            alert.setContentText(e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    private void linkGitHubJo(ActionEvent event) {
        try {
            new ProcessBuilder("x-www-browser", "https://github.com/JoaquinAyG").start();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al abrir la página");
            alert.setContentText(e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    private void linkGitHubRob(ActionEvent event) {
        try {
            new ProcessBuilder("x-www-browser", "https://github.com/xBaank").start();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al abrir la página");
            alert.setContentText(e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    private void linkGitHubFr(ActionEvent event) {
        try {
            new ProcessBuilder("x-www-browser", "https://github.com/frantoribio").start();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al abrir la página");
            alert.setContentText(e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    private void openProject(ActionEvent event) {
        try {
            new ProcessBuilder("x-www-browser", "https://github.com/Rochiio/ProyectoFinal1DAM").start();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al abrir la página");
            alert.setContentText(e.getMessage());
            e.printStackTrace();
        }
    }

}
