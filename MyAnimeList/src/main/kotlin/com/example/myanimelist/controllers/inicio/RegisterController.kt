package com.example.myanimelist.controllers.inicio

import com.example.myanimelist.extensions.loadScene
import com.example.myanimelist.extensions.show
import com.example.myanimelist.filters.login.RegisterFilters
import com.example.myanimelist.managers.CurrentUser
import com.example.myanimelist.models.User
import com.example.myanimelist.utils.HEIGHT
import com.example.myanimelist.utils.LOGIN
import com.example.myanimelist.utils.WIDTH
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.stage.Stage
import org.koin.core.component.inject
import java.time.LocalDate

class RegisterController : InicioController() {
    private val registerFilters: RegisterFilters by inject()
    private val user: CurrentUser by inject()

    @FXML
    private lateinit var txtEmail: TextField

    @FXML
    private lateinit var txtConfirmPassword: PasswordField

    /**
     * Cambiar la escena al login
     */
    fun changeSceneToLogin() {
        val stage = btnLogin.scene.window as Stage
        stage.loadScene(LOGIN, WIDTH, HEIGHT) {
            title = "Login"
            isResizable = false
        }.show()
    }


    /**
     * Realizar el registro
     */
    fun register() {
        val message = StringBuilder()

        if (!validateFields(message)) {
            Alert(Alert.AlertType.WARNING).show("Registro incorrecto", message.toString())
            return
        }

        if (createUser() == null) {
            Alert(Alert.AlertType.ERROR).show("Error al registrarse", "Error al crear usuario")
            return
        }

        Alert(Alert.AlertType.INFORMATION).show("Registro completado", "Bienvenido ${txtUsername.text}")
        changeSceneToLogin()
    }


    /**
     * Validación del Registro
     * @param errorMessage por si no pasase los filtros, mostrar el error
     * @return boolean dependiendo de si pasa los filtros correctamente o no
     */
    private fun validateFields(errorMessage: StringBuilder): Boolean {
        if (txtUsername.text.isNullOrBlank())
            errorMessage.appendLine("Username vacio")

        if (txtPassword.text != txtConfirmPassword.text)
            errorMessage.appendLine("Las contraseñas no coinciden")

        if (!registerFilters.checkPass(txtPassword.text))
            errorMessage.appendLine("La contraseña debe tener ${registerFilters.passLength} caracteres")

        if (!registerFilters.checkEmail(txtEmail.text))
            errorMessage.appendLine("Email incorrecto")

        if (registerFilters.checkUserCorrect(txtUsername.text))
            errorMessage.appendLine("Username ya existe")

        return errorMessage.isEmpty()
    }


    /**
     * Crear el usuario que se acaba de registrar
     */
    private fun createUser() = userRepository.add(
        User(
            txtUsername.text, txtEmail.text, txtPassword.text, LocalDate.now(), LocalDate.now(), null
        )
    )
}