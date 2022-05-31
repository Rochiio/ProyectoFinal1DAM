package com.example.myanimelist.filters.edition

import com.example.myanimelist.models.enums.Genre
import com.example.myanimelist.models.enums.Status
import java.time.LocalDate


open class EditFilters {
    fun checkEpisodesCorrect(number: String): Boolean = number.isNotBlank() && Regex("\\d+").matches(number)
    fun checkTitleCorrect(title: String): Boolean = title.isNotBlank()
    fun checkStatusCorrect(value: String?): Boolean =
        value?.isNotBlank() == true && Status.values().any { value == it.value }

    fun checkGenreCorrect(value: String?): Boolean =
        value?.isNotBlank() == true && Genre.values().any { value == it.value }

    fun checkDateCorrect(value: LocalDate?): Boolean = value != null && LocalDate.now().isAfter(value)

}