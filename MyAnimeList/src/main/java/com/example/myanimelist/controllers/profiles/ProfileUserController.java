package com.example.myanimelist.controllers.profiles;

import com.example.myanimelist.MyAnimeListApplication;
import com.example.myanimelist.extensions.AlertExtensionsKt;
import com.example.myanimelist.filters.FiltersUtilsKt;
import com.example.myanimelist.filters.edition.EditFilters;
import com.example.myanimelist.managers.DependenciesManager;
import com.example.myanimelist.models.User;
import com.example.myanimelist.repositories.users.IUsersRepository;
import com.example.myanimelist.utils.ThemesManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ProfileUserController {
    @FXML
    public TextField txtEmail;
    @FXML
    public TextField txtName;
    @FXML
    public PasswordField txtPassword;
    @FXML
    public PasswordField txtPasswordConfirm;
    @FXML
    public DatePicker txtBirthday;
    @FXML
    public Button btnSave;
    @FXML
    public ImageView img;
    @FXML
    public AnchorPane root;


    private final IUsersRepository userRepository = DependenciesManager.getUsersRepo();
    private final User user = DependenciesManager.globalUser;
    private final EditFilters editionFilters = DependenciesManager.getEditFilter();
    private final Logger logger = LogManager.getLogger(ProfileUserController.class);

    @FXML
    public void initialize() {
        txtEmail.setText(user.getEmail());
        txtName.setText(user.getName());
        txtPassword.setText(user.getPassword());
        txtBirthday.setValue(user.getBirthDate());
        root.getStylesheets().clear();
        root.getStylesheets().add(MyAnimeListApplication.class.getResource(ThemesManager.INSTANCE.getCurretnTheme().getValue()).toString());
    }

    public void onSave(ActionEvent actionEvent) {
        StringBuilder invalidMessage = new StringBuilder();
        if (!validate(invalidMessage)) {
            AlertExtensionsKt.show(
                    new Alert(Alert.AlertType.ERROR),
                    "Actualización inválida",
                    invalidMessage.toString()
            );
            return;
        }
        creationUpdateUser();
        AlertExtensionsKt.show(
                new Alert(Alert.AlertType.INFORMATION),
                "Actualización correcta",
                "Has actualizado tu perfil"
        );


    }

    private void creationUpdateUser() {
        User userUpdate = new User(txtName.getText(), txtEmail.getText(), txtPassword.getText(), user.getCreateDate(),
                txtBirthday.getValue(), user.getImg(), user.getMyList(), user.getId(), user.getAdmin());
        userRepository.update(userUpdate);
        DependenciesManager.globalUser = userUpdate;
    }


    private boolean validate(StringBuilder error) {
        if (!txtPassword.getText().equals(txtPasswordConfirm.getText()))
            error.append("Las contraseñas no coinciden");

        if (!editionFilters.checkDateCorrect(txtBirthday.getValue()))
            error.append("Fecha de nacimiento incorrecta");

        if (!FiltersUtilsKt.isValidEmail(txtEmail.getText()))
            error.append("Correo incorrecto");

        return error.isEmpty();
    }
}

