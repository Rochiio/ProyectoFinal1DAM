package com.example.myanimelist.controllers.inicio

import com.example.myanimelist.repositories.animeList.IRepositoryAnimeList
import com.example.myanimelist.repositories.users.IUsersRepository
import com.example.myanimelist.service.anime.IAnimeStorage
import javafx.fxml.FXML
import javafx.scene.control.Hyperlink
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

abstract class InicioController : KoinComponent{
    protected var userRepository = get<IUsersRepository>()
    protected var animesUserList = get<IRepositoryAnimeList>()

    @FXML
    protected lateinit var txtUsername: TextField

    @FXML
    protected lateinit var txtPassword: PasswordField

    @FXML
    protected lateinit var btnRegister: Hyperlink

    @FXML
    protected lateinit var btnLogin: Hyperlink
}