package com.example.myanimelist.controllers.inicio

import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.extensions.show
import com.example.myanimelist.filters.login.RegisterFilters
import com.example.myanimelist.managers.DependenciesManager.getRegisterFilter
import com.example.myanimelist.models.User
import com.example.myanimelist.utils.HEIGHT
import com.example.myanimelist.utils.LOGIN
import com.example.myanimelist.utils.WIDTH
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.stage.Stage
import java.time.LocalDate

class RegisterController : InicioController() {
    private var registerFilters: RegisterFilters = getRegisterFilter()

    @FXML
    private lateinit var txtEmail: TextField

    @FXML
    private lateinit var txtConfirmPassword: PasswordField

    fun changeSceneToLogin() {
        val stage = btnLogin.scene.window as Stage
        stage.loadScene(LOGIN, WIDTH, HEIGHT) {
            it.title = "Login"
            it.isResizable = false
        }.show()
    }

    fun register() {
        val message = StringBuilder()

        if (!validateFields(message)) {
            Alert(Alert.AlertType.WARNING).show("Register invalid", message.toString())
            return
        }

        if (createUser() == null) {
            Alert(Alert.AlertType.ERROR).show("Register error", "Error al crear usuario")
            return
        }

        Alert(Alert.AlertType.INFORMATION).show("Register completed", "Bienvenido ${txtUsername.text}")
    }

    private fun validateFields(errorMessage: StringBuilder): Boolean {
        if (txtUsername.text.isNullOrBlank())
            errorMessage.appendLine("Username vacio")

        if (txtPassword.text != txtConfirmPassword.text)
            errorMessage.appendLine("Las contraseñas no coinciden")

        if (!registerFilters.checkPass(txtPassword.text))
            errorMessage.appendLine("La contraseña debe tener ${registerFilters.passLength} caracteres")

        if (!registerFilters.checkEmail(txtEmail.text))
            errorMessage.appendLine("Email incorrecto")

        if (registerFilters.checkUserNameExists(txtUsername.text))
            errorMessage.appendLine("Username ya existe")

        return errorMessage.isEmpty()
    }

    private fun createUser() = userRepository.add(
        User(
            txtUsername.text, txtEmail.text, txtPassword.text, LocalDate.now(), LocalDate.now(), null
        )
    )
}