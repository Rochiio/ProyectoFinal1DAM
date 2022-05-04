package com.example.myanimelist.models

import java.util.*

data class Reviews (

    val id: UUID = UUID.randomUUID(),
    val idAnime: UUID,
    val idUser: UUID,
    var score: Int,
    var review: String

    )