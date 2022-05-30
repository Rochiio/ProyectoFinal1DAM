package com.example.myanimelist.utils.html

interface GeneratorHtml {
    /**
     * Crear el directorio que necesitamos
     */
    fun mkdir()

    /**
     * Para crear el html de las estadísticas
     * @param animeCount,topGenreCount,topTypeCount,topRatedAnime,botRatedAnime datos que necesitamos
     * para realizar el html correctamente
     */
    fun makeHtml(animeCount: String, topGenreCount:String, topTypeCount: String,topRatedAnime:String, botRatedAnime:String)
}