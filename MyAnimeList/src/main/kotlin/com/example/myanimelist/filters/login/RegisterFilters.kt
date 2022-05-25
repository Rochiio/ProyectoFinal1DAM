package com.example.myanimelist.filters.login

import com.example.myanimelist.filters.isValidEmail
import com.example.myanimelist.repositories.users.IUsersRepository

class RegisterFilters(private val usersRepository: IUsersRepository) {
    val passLength = 7
    fun checkUserCorrect(name: String) = com.example.myanimelist.filters.checkUserCorrect(name, usersRepository)
    fun checkEmail(email: String): Boolean = isValidEmail(email)
    fun checkPass(pass: String): Boolean = pass.length >= passLength
}