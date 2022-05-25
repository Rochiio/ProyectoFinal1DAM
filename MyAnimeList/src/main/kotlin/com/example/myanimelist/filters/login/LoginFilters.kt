package com.example.myanimelist.filters.login

import com.example.myanimelist.repositories.users.IUsersRepository

open class LoginFilters(private val usersRepository: IUsersRepository) {
    fun checkUserCorrect(name: String) = com.example.myanimelist.filters.checkUserCorrect(name, usersRepository)

    fun checkPasswordCorrect(name: String, pass: String) =
        usersRepository.findByName(name).any {
            it.password == pass
        }
}

