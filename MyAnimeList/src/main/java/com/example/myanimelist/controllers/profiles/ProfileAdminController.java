package com.example.myanimelist.controllers.profiles;

import com.example.myanimelist.extensions.AlertExtensionsKt;
import com.example.myanimelist.filters.FiltersUtilsKt;
import com.example.myanimelist.filters.login.RegisterFilters;
import com.example.myanimelist.managers.DependenciesManager;
import com.example.myanimelist.models.User;
import com.example.myanimelist.service.img.IImgStorage;
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

    private final User user = DependenciesManager.globalUser;
    private final RegisterFilters registerFilter = DependenciesManager.getRegisterFilter();

    IImgStorage imgStorage = DependenciesManager.getImgStorage();

    public void onSave() {
        StringBuilder errorLog = new StringBuilder();
        if (!validate(errorLog)) {
            AlertExtensionsKt.show(
                    new Alert(Alert.AlertType.WARNING),
                    "Registro invalido",
                    errorLog.toString());
            return;
        }
        this.user.setEmail(emailLabel.getText());
        this.user.setName(nameLabel.getText());
        this.user.setPassword(passLabel.getText());
    }

    private boolean validate(StringBuilder error) {

        if (nameLabel.getText().isBlank())
            error.append("El nombre no puede estar vacío.").append("\n");

        if (registerFilter.checkUserCorrect(nameLabel.getText()))
            error.append("El nombre ya existe.").append("\n");

        if (!registerFilter.checkPass(passLabel.getText()))
            error.append("La contraseña no puede estar vacía.").append("\n");

        if (!passLabel.getText().equals(confirmLabel.getText()))
            error.append("La confirmación no se corresponde.").append("\n");

        if (FiltersUtilsKt.checkEmail(emailLabel.getText()))
            error.append("Email no válido");

        return error.isEmpty();
    }
}
