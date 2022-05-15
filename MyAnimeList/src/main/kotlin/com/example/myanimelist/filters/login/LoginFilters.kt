package com.example.myanimelist.filters.login

import com.example.myanimelist.repositories.users.IUsersRepository
import javax.inject.Inject

open class LoginFilters @Inject constructor(protected val usersRepository: IUsersRepository) {
    fun checkUserCorrect(name: String, pass: String) = usersRepository.findByName(name).any { it.password == pass }
}

