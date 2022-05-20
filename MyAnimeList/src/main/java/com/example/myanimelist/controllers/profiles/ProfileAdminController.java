package com.example.myanimelist.controllers.profiles;

import com.example.myanimelist.managers.DependenciesManager;
import com.example.myanimelist.models.User;
import com.example.myanimelist.service.img.IImgStorage;
import com.example.myanimelist.utils.Filters;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ProfileAdminController {

    public TextField emailLabel;
    public TextField nameLabel;
    public TextField passLabel;
    public TextField confirmLabel;
    public Button saveBut;
    public Button deleteBut;
    public ImageView img;

    private final User user= DependenciesManager.globalUser;

    IImgStorage imgStorage = DependenciesManager.getImgStorage();

    public void onSave(ActionEvent actionEvent) {
        StringBuilder errorLog = new StringBuilder();
        if (!validate(errorLog)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Registro inválido" + errorLog);
            return;
        }
        this.user.setEmail(emailLabel.getText());
        this.user.setName(nameLabel.getText());
        this.user.setPassword(passLabel.getText());
    }
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
