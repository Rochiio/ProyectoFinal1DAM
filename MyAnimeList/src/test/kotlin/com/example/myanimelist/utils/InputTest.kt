package com.example.myanimelist.utils

import com.example.myanimelist.utils.Input.Companion.isCorrectWithRegex
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class InputTest{

    @Test
    fun testisCorrectWithRegex(){
        val res = isCorrectWithRegex("3","[1-3]")
        val resFalse = isCorrectWithRegex("4","[1-3]")
        assertAll(
            { assertTrue(res) },
            { assertFalse(resFalse) }
        )
    }
}