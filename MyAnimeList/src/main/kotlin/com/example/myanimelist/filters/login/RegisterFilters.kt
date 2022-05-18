package com.example.myanimelist.filters.login

import com.example.myanimelist.repositories.users.IUsersRepository

class RegisterFilters(usersRepository: IUsersRepository) : LoginFilters(usersRepository) {
    val passLength = 7
    fun checkEmail(email: String): Boolean = Regex("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$").matches(email)
    fun checkUserNameExists(name: String): Boolean = usersRepository.findByName(name).any { name == it.name }
    fun checkPass(pass: String): Boolean = pass.length >= passLength
}