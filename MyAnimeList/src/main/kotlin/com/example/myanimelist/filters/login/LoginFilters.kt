package com.example.myanimelist.filters.login

import com.example.myanimelist.repositories.users.IUsersRepository

open class LoginFilters(protected val usersRepository: IUsersRepository) {
    fun checkUserCorrect(name: String, pass: String) = usersRepository.findByName(name).any {
        it.name == name && it.password == pass
    }
}

