package com.example.myanimelist.managers

import com.example.myanimelist.models.Anime
import com.example.myanimelist.models.User
import com.example.myanimelist.views.models.AnimeView

class CurrentUser {
    /**
     * The current user
     */
    lateinit var value: User

    /**
     * current anime selected
     */
    lateinit var animeSelected: AnimeView


    val animelist: MutableList<Anime>
        get() {
            if (!this::value.isInitialized) return mutableListOf()
            return value.myList
        }
    val isAdmin: Boolean
        get() {
            if (!this::value.isInitialized) return false
            return value.admin
        }
}