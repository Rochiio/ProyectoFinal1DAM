package com.example.myanimelist.filters.edition

import com.example.myanimelist.models.enums.Genre
import com.example.myanimelist.models.enums.Status

open class EditFilters {
    fun checkEpisodesCorrect(number: String): Boolean {
        return number.matches(Regex("\\d*")) || number.isEmpty()
    }

    fun checkStatusCorrect(value: String): Boolean = Status.values().any { value == it.value } || value.isEmpty()


    fun checkGenreCorrect(value: String): Boolean = Genre.values().any { value == it.value } || value.isEmpty()

    fun checkDateCorrect(value: String): Boolean {
        return value.matches(Regex("^\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12]\\d|3[01])\$"))
                || value.isEmpty()
    }
}