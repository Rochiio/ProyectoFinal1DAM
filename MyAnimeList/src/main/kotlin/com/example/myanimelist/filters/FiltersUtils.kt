package com.example.myanimelist.filters


fun checkEmail(email: String) = Regex("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$").matches(email)
