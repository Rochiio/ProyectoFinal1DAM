package com.example.myanimelist.controllers.inicio

import com.example.myanimelist.managers.DependenciesManager.getAnimeListRepo
import com.example.myanimelist.managers.DependenciesManager.getUsersRepo
import com.example.myanimelist.repositories.animeList.IRepositoryAnimeList
import com.example.myanimelist.repositories.users.IUsersRepository
import javafx.fxml.FXML
import javafx.scene.control.Hyperlink
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField

abstract class InicioController {
    protected var userRepository: IUsersRepository = getUsersRepo()
    protected var animesUserList: IRepositoryAnimeList = getAnimeListRepo()

    @FXML
    protected lateinit var txtUsername: TextField

    @FXML
    protected lateinit var txtPassword: PasswordField

    @FXML
    protected lateinit var btnRegister: Hyperlink

    @FXML
    protected lateinit var btnLogin: Hyperlink
}