package com.example.myanimelist.controllers.profiles;

import com.example.myanimelist.managers.DependenciesManager;
import com.example.myanimelist.managers.SceneManager;
import com.example.myanimelist.models.User;
import com.example.myanimelist.service.img.IImgStorage;
import com.example.myanimelist.utils.Filters;
import com.example.myanimelist.utils.ViewPathsKt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


public class ProfileUserController {
    Logger logger = LogManager.getLogger();
    @FXML
    public TextField emailLabel;
    @FXML
    public TextField nameLabel;
    @FXML
    public TextField passLabel;
    @FXML
    public TextField confirmLabel;
    @FXML
    public Button saveBut;
    @FXML
    public ImageView img;
    @FXML
    public Label imgLabel;

    private final User user= DependenciesManager.globalUser;
    IImgStorage imgStorage = DependenciesManager.getImgStorage();


    public void onSave(ActionEvent actionEvent) throws IOException {
        StringBuilder errorLog = new StringBuilder();
        if (!validate(errorLog)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Registro inválido" + errorLog);
            return;
        }
        this.user.setEmail(emailLabel.getText());
        this.user.setName(nameLabel.getText());
        this.user.setPassword(passLabel.getText());
        //changeSceneToMain();
        //todo incorporar la img
    }

    @FXML
    public void initialize() {
        emailLabel.setText(user.getEmail());
        nameLabel.setText(user.getName());
        img.setImage(imgStorage.loadImg(user));
    }

    /*private void changeSceneToMain() throws IOException {
        Stage stage = (Stage) saveBut.getScene().getWindow();
        SceneManager sceneManager = SceneManager.INSTANCE;
        sceneManager.changeScene(saveBut, ViewPathsKt.MAIN_USER_MYLIST);
        *//*val stage = menuButton.scene.window as Stage
        stage.loadScene(LOGIN){
            title = "Log in"
            isResizable = false
        }.show()*//*

    }*/
    private boolean validate(StringBuilder error) {

        boolean validation = true;

        if (nameLabel.getText().isBlank()) {
            validation = false;
            error.append("El nombre no puede estar vacío.").append("\n");
        }

        if (emailLabel.getText().isBlank()) {
            validation = false;
            error.append("El email no puede estar vacío.").append("\n");
        }

        if (passLabel.getText().isBlank()) {
            validation = false;
            error.append("La contraseña no puede estar vacía.").append("\n");
        }

        if (!(passLabel.getText().equals(confirmLabel.getText()))) {
            validation = false;
            error.append("La confirmación no se corresponde.").append("\n");
        }

        if (!Filters.checkEmail(emailLabel.getText())) {
            validation = false;
            error.append("Email no válido");
        }


        return validation;
    }
}

