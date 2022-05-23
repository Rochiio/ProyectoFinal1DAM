package com.example.myanimelist.filters


fun isValidEmail(email: String) = Regex("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$").matches(email)
