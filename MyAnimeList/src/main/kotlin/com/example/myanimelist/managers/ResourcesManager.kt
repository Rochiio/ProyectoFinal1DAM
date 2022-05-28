package com.example.myanimelist.managers

import com.example.myanimelist.MyAnimeListApplication
import java.io.InputStream

object ResourcesManager {

    fun getCoverOf(value: String?): InputStream? =
        MyAnimeListApplication::class.java.getResourceAsStream("covers/$value")

    fun getIconOf(value: String): InputStream? = MyAnimeListApplication::class.java.getResourceAsStream("icons/$value")
    fun getStyleOf(value: String): InputStream? =
        MyAnimeListApplication::class.java.getResourceAsStream("styles/$value")

    fun getViewsOf(value: String): InputStream? = MyAnimeListApplication::class.java.getResourceAsStream("views/$value")
    fun getImageOf(value: String): InputStream? =
        MyAnimeListApplication::class.java.getResourceAsStream("images/$value")

    fun getUserImageOf(value: String?): InputStream? =
        MyAnimeListApplication::class.java.getResourceAsStream("images/user/$value")

}
