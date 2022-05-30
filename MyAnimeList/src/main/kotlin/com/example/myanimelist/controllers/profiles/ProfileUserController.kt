package com.example.myanimelist.controllers.profiles

import com.example.myanimelist.MyAnimeListApplication
import com.example.myanimelist.extensions.show
import com.example.myanimelist.filters.isValidEmail
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.managers.DependenciesManager.getEditFilter
import com.example.myanimelist.managers.DependenciesManager.getImgStorage
import com.example.myanimelist.managers.DependenciesManager.getUsersRepo
import com.example.myanimelist.models.User
import com.example.myanimelist.utils.Properties
import com.example.myanimelist.utils.ThemesManager.getCurretnTheme
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.stage.FileChooser
import javafx.stage.Stage
import org.apache.logging.log4j.LogManager
import java.util.*

class ProfileUserController {
    @FXML
    var txtEmail: TextField? = null

    @FXML
    var txtName: TextField? = null

    @FXML
    var txtPassword: PasswordField? = null

    @FXML
    var txtPasswordConfirm: PasswordField? = null

    @FXML
    var txtBirthday: DatePicker? = null

    @FXML
    var btnSave: Button? = null

    @FXML
    var img: ImageView? = null

    @FXML
    var root: AnchorPane? = null
    private val userRepository = getUsersRepo()
    private val user = DependenciesManager.globalUser
    private val imgStorage = getImgStorage()
    private val editionFilters = getEditFilter()
    private val logger = LogManager.getLogger(
        ProfileUserController::class.java
    )

    @FXML
    fun initialize() {
        txtEmail!!.text = user.email
        txtName!!.text = user.name
        txtPassword!!.text = user.password
        txtBirthday!!.value = user.birthDate
        img!!.image = imgStorage.loadImg(user)
        root!!.stylesheets.clear()
        root!!.stylesheets.add(
            Objects.requireNonNull(MyAnimeListApplication::class.java.getResource(getCurretnTheme().value)).toString()
        )
    }

    fun onSave() {
        val invalidMessage = StringBuilder()
        if (!validate(invalidMessage)) {
            Alert(Alert.AlertType.ERROR).show("Actualización inválida", invalidMessage.toString())
            return
        }
        creationUpdateUser()
        Alert(Alert.AlertType.INFORMATION).show("Actualización correcta", "Has actualizado tu perfil")
        val stage = btnSave!!.scene.window as Stage
        stage.close()
    }


    /**
     * Actualizar datos del usuario
     */
    private fun creationUpdateUser() {
        val userUpdate = User(
            txtName!!.text, txtEmail!!.text, txtPassword!!.text, user.createDate,
            txtBirthday!!.value, user.img, user.myList, user.id, user.admin
        )
        userRepository.update(userUpdate)
        DependenciesManager.globalUser = userUpdate
    }

    /**
     * Filtrado de datos
     * @param error mensaje de error que va a mostrar si hay campos incorrectos
     * @return boolean dependiendo de si pasa el filtrado correctamente
     */
    private fun validate(error: StringBuilder): Boolean {
        if (txtPassword!!.text != txtPasswordConfirm!!.text) error.append("Las contraseñas no coinciden")
        if (!editionFilters.checkDateCorrect(txtBirthday!!.value)) error.append("Fecha de nacimiento incorrecta")
        if (!isValidEmail(txtEmail!!.text)) error.append("Correo incorrecto")
        return error.isEmpty()
    }


    /**
     * Cambiar la imagen de perfil del usuario
     */
    fun changeUserImg() {
        val fc = FileChooser()
        fc.title = "Selecciona una nueva imagen"
        fc.extensionFilters.addAll(
            FileChooser.ExtensionFilter("Imagenes png", "*.png"),
            FileChooser.ExtensionFilter("Imagenes jpg", "*.jpg")
        )
        val file = fc.showOpenDialog(img!!.scene.window)
        if (file != null) {
            logger.info(file.absolutePath)
            img!!.image = Image(file.toURI().toString())
            user.img = file.name
            imgStorage.cpFile(file, Properties.USER_IMG_DIR)
        }
    }
}