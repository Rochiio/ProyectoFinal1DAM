package com.example.myanimelist.controllers.profiles;

import com.example.myanimelist.models.User;
import com.example.myanimelist.service.img.ImgStorage;
import com.example.myanimelist.utils.Filters;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ProfileUserController {
    public TextField emailLabel;
    public TextField nameLabel;
    public TextField passLabel;
    public TextField confirmLabel;
    public Button saveBut;
    public ImageView img;
    public Label imgLabel;

    private final User user;
    ImgStorage imgStorage = new ImgStorage();

    public ProfileUserController(User user){
        this.user=user;
    }
    public void onSave(ActionEvent actionEvent) {
        StringBuilder errorLog = new StringBuilder();
        if (!validate(errorLog)) {
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Registro inválido" + errorLog);
            return;
        }
        this.user.setEmail(emailLabel.getText());
        this.user.setName(nameLabel.getText());
        this.user.setPassword(passLabel.getText());
//todo incorporar la img
    }
    @FXML
    public void initialize() {
        emailLabel.setText(user.getEmail());
        nameLabel.setText(user.getName());
        img.setImage(imgStorage.loadImg(user));
    }
    public void changeImg(MouseEvent mouseEvent) {
        if ((mouseEvent.getButton() == MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {

        }
    }

    public void showLabel(MouseEvent mouseEvent) {
    }

    private boolean validate(StringBuilder error) {

        boolean validation=true;

        if (nameLabel.getText().isBlank()) {
            validation=false;
            error.append("El nombre no puede estar vacío.").append("\n");
        }

        if (emailLabel.getText().isBlank()) {
            validation=false;
            error.append("El email no puede estar vacío.").append("\n");
        }

        if (passLabel.getText().isBlank()) {
            validation=false;
            error.append("La contraseña no puede estar vacía.").append("\n");
        }

        if (!(passLabel.getText().equals(confirmLabel.getText()))) {
            validation=false;
            error.append("La confirmación no se corresponde.").append("\n");
        }

        if (!Filters.checkEmail(emailLabel.getText())) {
            validation=false;
            error.append("Email no válido");
        }



        return validation;
    }
}
