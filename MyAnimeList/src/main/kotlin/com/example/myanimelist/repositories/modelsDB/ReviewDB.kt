package com.example.myanimelist.repositories.modelsDB

import java.util.*

internal data class ReviewDB(
    val id: UUID = UUID.randomUUID(),
    val idAnime: UUID,
    val idUser: UUID,
    var score: Int,
    var comment: String
)