package com.example.myanimelist.models

import java.util.*

data class Reviews (

    val id: UUID = UUID.randomUUID(),
    val idAnime: Int,
    val idUser: UUID,
    var score: Int,

    )