package com.example.myanimelist.utils.html

import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class GeneratorHtmlStats: GeneratorHtml{
    private val current : Path = Paths.get("")
    private val directory:String = current.toAbsolutePath().toString() + File.separator + "estadisticas"
    private val fileHtml: String= directory + File.separator + "estadisticas.html"
    private val html :HTMLGenerator = HTMLGenerator()


    init {
        mkdir()
    }


    override fun mkdir() {
        val path : Path = Path.of(directory)
        if (!Files.exists(path)){
            Files.createDirectories(path)
        }
    }



    override fun makeHtml(animeCount: String, topGenreCount: String, topTypeCount: String, topRatedAnime: String, botRatedAnime: String) {
        val pw = PrintWriter(FileWriter(fileHtml))
        pw.println(html.HEAD)
        pw.println(html.BODY)
        pw.println("""
            <h3>Anime más gustado: </h3>
            <h4>
        """ + topRatedAnime + """</h4>""")
        pw.println("""
            <h3>Anime menos gustado: </h3>
            <h4>
        """ + botRatedAnime + """</h4>""")
        pw.println("""
            <h3>Total de animes vistos: </h3>
            <h4>
        """ + animeCount + """</h4>""")
        pw.println("""
            <h3>Género favorito: </h3>
            <h4>
        """ + topGenreCount + """</h4>""")
        pw.println("""
            <h3>Tipo de anime favorito: </h3>
            <h4>
        """ + topTypeCount + """</h4>""")
        pw.println(html.BODY_CLOSE)

     pw.close()
    }
}