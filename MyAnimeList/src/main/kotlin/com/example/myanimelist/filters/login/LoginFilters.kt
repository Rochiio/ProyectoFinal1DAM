package com.example.myanimelist.filters.login

import com.example.myanimelist.repositories.users.IUsersRepository

open class LoginFilters(private val usersRepository: IUsersRepository) {
    fun checkUserCorrect(name: String) = usersRepository.findByName(name).any {
        it.name == name
    }

    fun checkPasswordCorrect(name: String, pass: String) = usersRepository.findByName(name).any {
        it.password == pass
    }
}

