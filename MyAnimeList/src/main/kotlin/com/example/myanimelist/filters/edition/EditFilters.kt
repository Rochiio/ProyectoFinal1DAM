package com.example.myanimelist.filters.edition

import com.example.myanimelist.models.enums.Genre
import com.example.myanimelist.models.enums.Status
import java.time.LocalDate

open class EditFilters {
    fun checkEpisodesCorrect(number: String): Boolean {
        return number.matches(Regex("\\d*")) || number.isEmpty()
    }

    fun checkStatusCorrect(value: String): Boolean = Status.values().any { value == it.value } || value.isEmpty()


    fun checkGenreCorrect(value: String): Boolean = Genre.values().any { value == it.value } || value.isEmpty()

    fun checkDateCorrect(value: LocalDate): Boolean = LocalDate.now().isAfter(value)

}