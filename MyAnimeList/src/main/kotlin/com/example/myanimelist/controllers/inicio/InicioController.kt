package com.example.myanimelist.controllers.inicio

import com.example.myanimelist.repositories.users.IUsersRepository
import javafx.fxml.FXML
import javafx.scene.control.Hyperlink
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import org.koin.java.KoinJavaComponent.inject

abstract class InicioController {
    protected val userRepository: IUsersRepository by inject(IUsersRepository::class.java)

    @FXML
    protected lateinit var txtUsername: TextField

    @FXML
    protected lateinit var txtPassword: PasswordField

    @FXML
    protected lateinit var btnRegister: Hyperlink

    @FXML
    protected lateinit var btnLogin: Hyperlink
}