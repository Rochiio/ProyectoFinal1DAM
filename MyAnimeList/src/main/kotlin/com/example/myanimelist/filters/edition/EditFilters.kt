package com.example.myanimelist.filters.edition

import com.example.myanimelist.models.enums.Genre
import com.example.myanimelist.models.enums.Status
import java.time.LocalDate

open class EditFilters {
    fun checkEpisodesCorrect(number: String): Boolean = number.matches(Regex("\\d*")) || number.isNotEmpty()
    fun checkTitleCorrect(title: String): Boolean = title.isNotBlank()

    fun checkStatusCorrect(value: String): Boolean = Status.values().any { value == it.value } || value.isNotEmpty()

    fun checkGenreCorrect(value: String): Boolean = Genre.values().any { value == it.value } || value.isNotEmpty()

    fun checkDateCorrect(value: LocalDate?): Boolean = value != null && LocalDate.now().isAfter(value)

}