package com.example.myanimelist.filters.edition

import com.example.myanimelist.managers.DependenciesManager
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class EditFiltersTest {
    private var editFilters: EditFilters= DependenciesManager.getEditFilter()

    @Test
    fun checkEpisodesCorrect() {
        val correct = "8"
        val correctTwo =""
        val bad = "bad"
        assertAll(
            { assertTrue(editFilters.checkEpisodesCorrect(correct)) },
            { assertTrue(editFilters.checkEpisodesCorrect(correctTwo))},
            { assertFalse(editFilters.checkEpisodesCorrect(bad)) }
        )
    }

    @Test
    fun checkStatusCorrect() {
        val correct ="Finished Airing"
        val correctTwo="Currently Airing"
        val correctThree="Not yet aired"
        val correctFour =""
        val bad = "bad"
        assertAll(
            { assertTrue(editFilters.checkStatusCorrect(correct))},
            { assertTrue(editFilters.checkStatusCorrect(correctTwo))},
            { assertTrue(editFilters.checkStatusCorrect(correctThree))},
            { assertTrue(editFilters.checkStatusCorrect(correctFour))},
            {assertFalse(editFilters.checkStatusCorrect(bad))}
            )
    }

    @Test
    fun checkGenreCorrect() {
        val correct ="Adventure"
        val correctTwo ="Action"
        val correctThree ="Comedy"
        val correctFour ="Slice of life"
        val correctFive ="Drama"
        val correctSix ="Fantasy"
        val correctSeven ="Supernatural"
        val correctEight ="Magic"
        val correctNine ="Mystery"
        val correctTen ="Horror"
        val correctEleven ="Psychological"
        val correctTwelve  ="Sci-Fi"
        val correcttThirteen ="Romance"
        val correctFourteen =""
        val bad = "bad"
        assertAll(
            { assertTrue(editFilters.checkGenreCorrect(correct))},
            { assertTrue(editFilters.checkGenreCorrect(correctTwo))},
            { assertTrue(editFilters.checkGenreCorrect(correctThree))},
            { assertTrue(editFilters.checkGenreCorrect(correctFour))},
            { assertTrue(editFilters.checkGenreCorrect(correctFive))},
            { assertTrue(editFilters.checkGenreCorrect(correctSix))},
            { assertTrue(editFilters.checkGenreCorrect(correctSeven))},
            { assertTrue(editFilters.checkGenreCorrect(correctEight))},
            { assertTrue(editFilters.checkGenreCorrect(correctNine))},
            { assertTrue(editFilters.checkGenreCorrect(correctTen))},
            { assertTrue(editFilters.checkGenreCorrect(correctEleven))},
            { assertTrue(editFilters.checkGenreCorrect(correctTwelve))},
            { assertTrue(editFilters.checkGenreCorrect(correcttThirteen))},
            { assertTrue(editFilters.checkGenreCorrect(correctFourteen))},
            {assertFalse(editFilters.checkGenreCorrect(bad))}
        )
    }

    @Test
    fun checkDateCorrect() {
        val correct="2022-03-20"
        val correctTwo="2022-05-21"
        val correctThree="2012-06-14"
        val correctFour=""
        val bad="bad"
        assertAll(
            {assertTrue(editFilters.checkDateCorrect(correct))},
            {assertTrue(editFilters.checkDateCorrect(correctTwo))},
            {assertTrue(editFilters.checkDateCorrect(correctThree))},
            {assertTrue(editFilters.checkDateCorrect(correctFour))},
            {assertFalse(editFilters.checkDateCorrect(bad))}
        )
    }
    

}