package com.example.myanimelist.filters.login

import com.example.myanimelist.filters.isValidEmail
import com.example.myanimelist.repositories.users.IUsersRepository

class RegisterFilters(usersRepository: IUsersRepository) : LoginFilters(usersRepository) {
    val passLength = 7
    fun checkEmail(email: String): Boolean = isValidEmail(email)
    fun checkPass(pass: String): Boolean = pass.length >= passLength
}