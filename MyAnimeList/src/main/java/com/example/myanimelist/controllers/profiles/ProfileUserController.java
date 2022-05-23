package com.example.myanimelist.controllers.profiles;

import com.example.myanimelist.MyAnimeListApplication;
import com.example.myanimelist.filters.FiltersUtilsKt;
import com.example.myanimelist.filters.edition.EditFilters;
import com.example.myanimelist.managers.DependenciesManager;
import com.example.myanimelist.models.User;
import com.example.myanimelist.repositories.users.IUsersRepository;
import com.example.myanimelist.utils.ThemesManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;


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
    public TextField txtBirthday;
    @FXML
    public Button btnSave;
    @FXML
    public ImageView img;
    @FXML
    public AnchorPane root;

    private final IUsersRepository userRepository = DependenciesManager.getUsersRepo();
    private final User user = DependenciesManager.globalUser;
    private final EditFilters editionFilters = DependenciesManager.getEditFilter();
    private final Logger logger = DependenciesManager.getLogger(ProfileUserController.class);

    @FXML
    public void initialize() {
        txtEmail.setText(user.getEmail());
        txtName.setText(user.getName());
        txtPassword.setText(user.getPassword());
        txtBirthday.setText(user.getBirthDate().toString());
        root.getStylesheets().clear();
        root.getStylesheets().add(MyAnimeListApplication.class.getResource(ThemesManager.INSTANCE.getCurretnTheme().getValue()).toString());
    }

    public void onSave() {
        StringBuilder errorLog = new StringBuilder();
        if (!validate(errorLog)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Actualización inválida");
            alert.setHeaderText(errorLog.toString());
            alert.show();
            return;
        }
        creationUpdateUser();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Actualización correcta");
        alert.show();
    }

    private void creationUpdateUser() {
        User userUpdate = new User(txtName.getText(), txtEmail.getText(), txtPassword.getText(), user.getCreateDate(),
                LocalDate.parse(txtBirthday.getText()), user.getImg(), user.getMyList(), user.getId(), user.getAdmin());
        userRepository.update(userUpdate);
        DependenciesManager.globalUser = userUpdate;
    }


    private boolean validate(StringBuilder error) {
        if (!txtPassword.getText().equals(txtPasswordConfirm.getText()))
            error.append("Las contraseñas no coinciden");

        if (!editionFilters.checkDateCorrect(txtBirthday.getText()))
            error.append("Fecha de nacimiento incorrecta");

        if (!FiltersUtilsKt.checkEmail(txtEmail.getText()))
            error.append("Correo incorrecto");

        return error.isEmpty();
    }
}

