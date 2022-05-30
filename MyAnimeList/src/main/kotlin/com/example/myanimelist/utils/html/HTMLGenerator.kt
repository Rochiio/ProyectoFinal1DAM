package com.example.myanimelist.utils.html

class HTMLGenerator {
     val HEAD = """
            <!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="autor" content="My Anime List">
    <title>Estadísticas My Anime List</title>
</head>
    """


    val BODY = """
    <body bgcolor="#99E2B4">
      <img src="../src/main/resources/com/example/myanimelist/images/logo/Login.png" alt="Logo" style="width:500px;height:300px;">
        <h1>Estadísticas</h1>
   """

    val BODY_CLOSE = """</body>
       </html>
   """
}