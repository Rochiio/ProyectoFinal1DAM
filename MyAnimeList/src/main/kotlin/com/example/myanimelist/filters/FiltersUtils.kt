package com.example.myanimelist.filters

import com.example.myanimelist.repositories.users.IUsersRepository


fun isValidEmail(email: String) = Regex("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$").matches(email)
fun checkUserCorrect(name: String, usersRepository: IUsersRepository) = usersRepository.findByName(name).any {
    it.name == name
}
