package com.example.myanimelist.models

data class Reviews (

    val id: Int,
    val anime: Anime,
    val user: User,
    var score: Int,

    )