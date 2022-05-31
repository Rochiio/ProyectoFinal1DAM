package com.example.myanimelist.controllers.profiles

import com.example.myanimelist.MyAnimeListApplication
import com.example.myanimelist.extensions.getLogger
import com.example.myanimelist.extensions.show
import com.example.myanimelist.filters.edition.EditFilters
import com.example.myanimelist.filters.isValidEmail
import com.example.myanimelist.managers.CurrentUser
import com.example.myanimelist.models.User
import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.service.img.IImgStorage
import com.example.myanimelist.utils.Properties
import com.example.myanimelist.utils.ThemesManager.getCurretnTheme
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.stage.FileChooser
import javafx.stage.Stage
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import java.util.*

class ProfileUserController : KoinComponent {
    @FXML
    lateinit var txtEmail: TextField

    @FXML
    lateinit var txtName: TextField

    @FXML
    lateinit var txtPassword: PasswordField

    @FXML
    lateinit var txtPasswordConfirm: PasswordField

    @FXML
    lateinit var txtBirthday: DatePicker

    @FXML
    lateinit var btnSave: Button

    @FXML
    lateinit var img: ImageView

    @FXML
    lateinit var root: AnchorPane

    private val userRepository: IUsersRepository by inject()
    private val user: CurrentUser by inject()
    private val imgStorage = get<IImgStorage>()
    private val editionFilters: EditFilters by inject()
    private val logger = getLogger<ProfileUserController>()

    @FXML
    fun initialize() {
        txtEmail.text = user.value.email
        txtName.text = user.value.name
        txtPassword.text = user.value.password
        txtBirthday.value = user.value.birthDate
        img.image = imgStorage.loadImg(user.value)
        root.stylesheets.clear()
        root.stylesheets.add(
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
        val stage = btnSave.scene.window as Stage
        stage.close()
    }


    /**
     * Actualizar datos del usuario
     */
    private fun creationUpdateUser() {
        val userUpdate = User(
            txtName.text, txtEmail.text, txtPassword.text, user.value.createDate,
            txtBirthday.value, user.value.img, user.value.myList, user.value.id, user.value.admin
        )

        val updated = userRepository.update(userUpdate)
        if (updated != null)
            user.value = userUpdate
    }

    /**
     * Filtrado de datos
     * @param error mensaje de error que va a mostrar si hay campos incorrectos
     * @return boolean dependiendo de si pasa el filtrado correctamente
     */
    private fun validate(error: StringBuilder): Boolean {
        if (txtPassword.text != txtPasswordConfirm.text) error.append("Las contraseñas no coinciden")
        if (!editionFilters.checkDateCorrect(txtBirthday.value)) error.append("Fecha de nacimiento incorrecta")
        if (!isValidEmail(txtEmail.text)) error.append("Correo incorrecto")
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
        val file = fc.showOpenDialog(img.scene.window)
        if (file != null) {
            logger.info(file.absolutePath)
            img.image = Image(file.toURI().toString())
            user.value.img = file.name
            imgStorage.cpFile(file, Properties.USER_IMG_DIR)
        }
    }
}