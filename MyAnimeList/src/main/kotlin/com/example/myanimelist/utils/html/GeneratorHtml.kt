package com.example.myanimelist.utils.html

interface GeneratorHtml {
    fun mkdir()
    fun makeHtml(animeCount: String, topGenreCount:String, topTypeCount: String,topRatedAnime:String, botRatedAnime:String)
}