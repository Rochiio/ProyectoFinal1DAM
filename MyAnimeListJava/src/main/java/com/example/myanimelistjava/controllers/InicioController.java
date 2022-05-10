package com.example.myanimelistjava.controllers;

import com.example.myanimelistjava.MyAnimeListApplication;
import com.example.myanimelistjava.configurations.ViewConfig;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioController {
    @FXML
    TextField txtUsername;
    @FXML
    PasswordField txtPassword;
    @FXML
    TextField txtUsernameRegister;
    @FXML
    TextField txtEmail;
    @FXML
    PasswordField txtPasswordRegister;
    @FXML
    PasswordField txtConfirmPassword;
    @FXML
    Hyperlink btnRegister;
    public void changeSceneRegister(ActionEvent actionEvent) throws IOException {
        Platform.setImplicitExit(true);
        var stage = (Stage) btnRegister.getScene().getWindow();
        FXMLLoader fxmlLoader= new FXMLLoader(MyAnimeListApplication.class.getResource("views/registro-view.fxml"));
        var scene = new Scene(fxmlLoader.load(), Double.parseDouble(ViewConfig.WIDTH.getValue()), Double.parseDouble(ViewConfig.HEIGHT.getValue()));
        stage.setTitle("Registro");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
