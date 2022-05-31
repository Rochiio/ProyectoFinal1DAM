package com.example.myanimelist.filters.edition

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.koin.test.KoinTest
import java.time.LocalDate

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class EditFiltersTest : KoinTest {
    private var editFilters = EditFilters()

    @BeforeAll
    fun start() {

        //editFilters = get()
    }

    @Test
    fun checkEpisodesCorrect() {
        val correct = "8"
        val bad = "bad"
        assertTrue(editFilters.checkEpisodesCorrect(correct))
        assertFalse(editFilters.checkEpisodesCorrect(bad))
    }

    @Test
    fun checkStatusCorrect() {
        val correct = "Finished Airing"
        val correctTwo = "Currently Airing"
        val correctThree = "Not yet aired"
        val bad = "bad"
        assertAll(
            { assertTrue(editFilters.checkStatusCorrect(correct)) },
            { assertTrue(editFilters.checkStatusCorrect(correctTwo)) },
            { assertTrue(editFilters.checkStatusCorrect(correctThree)) },
            { assertFalse(editFilters.checkStatusCorrect(bad)) }
        )
    }

    @Test
    fun checkGenreCorrect() {
        val correct = "Adventure"
        val correctTwo = "Action"
        val correctThree = "Comedy"
        val correctFour = "Slice of life"
        val correctFive = "Drama"
        val correctSix = "Fantasy"
        val correctSeven = "Supernatural"
        val correctEight = "Magic"
        val correctNine = "Mystery"
        val correctTen = "Horror"
        val correctEleven = "Psychological"
        val correctTwelve = "Sci-Fi"
        val correcttThirteen = "Romance"
        val bad = "bad"
        assertAll(
            { assertTrue(editFilters.checkGenreCorrect(correct)) },
            { assertTrue(editFilters.checkGenreCorrect(correctTwo)) },
            { assertTrue(editFilters.checkGenreCorrect(correctThree)) },
            { assertTrue(editFilters.checkGenreCorrect(correctFour)) },
            { assertTrue(editFilters.checkGenreCorrect(correctFive)) },
            { assertTrue(editFilters.checkGenreCorrect(correctSix)) },
            { assertTrue(editFilters.checkGenreCorrect(correctSeven)) },
            { assertTrue(editFilters.checkGenreCorrect(correctEight)) },
            { assertTrue(editFilters.checkGenreCorrect(correctNine)) },
            { assertTrue(editFilters.checkGenreCorrect(correctTen)) },
            { assertTrue(editFilters.checkGenreCorrect(correctEleven)) },
            { assertTrue(editFilters.checkGenreCorrect(correctTwelve)) },
            { assertTrue(editFilters.checkGenreCorrect(correcttThirteen)) },
            { assertFalse(editFilters.checkGenreCorrect(bad)) }
        )
    }

    @Test
    fun checkDateCorrect() {
        val correct = LocalDate.of(2002, 10, 10)
        val wrong = LocalDate.now()
        val wrong2 = LocalDate.of(9999, 10, 10)
        assertAll(
            { assertTrue(editFilters.checkDateCorrect(correct)) },
            { assertFalse(editFilters.checkDateCorrect(wrong)) },
            { assertFalse(editFilters.checkDateCorrect(wrong2)) }
        )
    }


}