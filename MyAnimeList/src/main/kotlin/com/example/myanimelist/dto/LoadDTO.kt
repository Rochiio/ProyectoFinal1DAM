package com.example.myanimelist.dto

class LoadDTO(var isLoaded: Boolean, var isNightMode: Boolean) {

    override fun toString(): String {
        return """
               loaded=${isLoaded}
               nightMode=${isNightMode}
               """.trimIndent()
    }
}