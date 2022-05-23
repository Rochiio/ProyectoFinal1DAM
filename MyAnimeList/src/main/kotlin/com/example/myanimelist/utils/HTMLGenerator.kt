package com.example.myanimelist.utils

class HTMLGenerator {
    private val HEAD = """
            <!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="autor" content="My Anime List">
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Gráficos y Estadísticas</title>
</head>
    """


    private val BODY = """
      <body>
       <h1>Gráficos y Estadísticas</h1>
   """

    private val BODY_CLOSE = """</body>
       </html>
   """
}