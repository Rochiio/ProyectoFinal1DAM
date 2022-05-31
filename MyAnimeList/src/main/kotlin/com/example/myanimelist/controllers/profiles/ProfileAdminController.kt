package com.example.myanimelist.controllers.profiles

import com.example.myanimelist.extensions.show
import com.example.myanimelist.filters.isValidEmail
import com.example.myanimelist.filters.login.RegisterFilters
import com.example.myanimelist.managers.CurrentUser
import com.example.myanimelist.service.img.IImgStorage
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProfileAdminController : KoinComponent {
    @FXML
    private lateinit var emailLabel: TextField

    @FXML
    private lateinit var nameLabel: TextField

    @FXML
    private lateinit var passLabel: TextField

    @FXML
    private lateinit var confirmLabel: TextField

    @FXML
    private lateinit var saveBut: Button

    @FXML
    private lateinit var deleteBut: Button

    @FXML
    private lateinit var img: ImageView

    private val registerFilter: RegisterFilters by inject()
    private val imgStorage: IImgStorage by inject()
    private val user: CurrentUser by inject()


    /**
     * Guardar cambios perfil administrador
     */
    fun onSave() {
        val errorLog = StringBuilder()
        if (!validate(errorLog)) {
            Alert(Alert.AlertType.WARNING).show("Registro invalido", errorLog.toString())
            return
        }
        user.value.email = emailLabel.text
        user.value.name = nameLabel.text
        user.value.password = passLabel.text
    }


    /**
     * Filtrado de datos
     * @param error mensaje de error que va a mostrar si hay campos incorrectos
     * @return boolean dependiendo de si pasa el filtrado correctamente o no
     */
    private fun validate(error: StringBuilder): Boolean {
        if (nameLabel.text.isBlank()) error.append("El nombre no puede estar vacío.").append("\n")
        if (registerFilter.checkUserCorrect(nameLabel.text)) error.append("El nombre ya existe.").append("\n")
        if (!registerFilter.checkPass(passLabel.text)) error.append("La contraseña no puede estar vacía.")
            .append("\n")
        if (passLabel.text != confirmLabel.text) error.append("La confirmación no se corresponde.").append("\n")
        if (isValidEmail(emailLabel.text)) error.append("Email no válido")
        return error.isEmpty()
    }
}