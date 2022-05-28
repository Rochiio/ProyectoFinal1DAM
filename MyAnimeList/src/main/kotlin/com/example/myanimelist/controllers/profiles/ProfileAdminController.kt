package com.example.myanimelist.controllers.profiles

import com.example.myanimelist.extensions.show
import com.example.myanimelist.filters.isValidEmail
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.managers.DependenciesManager.getImgStorage
import com.example.myanimelist.managers.DependenciesManager.getRegisterFilter
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.image.ImageView

class ProfileAdminController {
    var emailLabel: TextField? = null
    var nameLabel: TextField? = null
    var passLabel: TextField? = null
    var confirmLabel: TextField? = null
    var saveBut: Button? = null
    var deleteBut: Button? = null
    var img: ImageView? = null
    private val user = DependenciesManager.globalUser
    private val registerFilter = getRegisterFilter()
    var imgStorage = getImgStorage()
    fun onSave() {
        val errorLog = StringBuilder()
        if (!validate(errorLog)) {
            Alert(Alert.AlertType.WARNING).show("Registro invalido", errorLog.toString())
            return
        }
        user.email = emailLabel!!.text
        user.name = nameLabel!!.text
        user.password = passLabel!!.text
    }

    private fun validate(error: StringBuilder): Boolean {
        if (nameLabel!!.text.isBlank()) error.append("El nombre no puede estar vacío.").append("\n")
        if (registerFilter.checkUserCorrect(nameLabel!!.text)) error.append("El nombre ya existe.").append("\n")
        if (!registerFilter.checkPass(passLabel!!.text)) error.append("La contraseña no puede estar vacía.")
            .append("\n")
        if (passLabel!!.text != confirmLabel!!.text) error.append("La confirmación no se corresponde.").append("\n")
        if (isValidEmail(emailLabel!!.text)) error.append("Email no válido")
        return error.isEmpty()
    }
}