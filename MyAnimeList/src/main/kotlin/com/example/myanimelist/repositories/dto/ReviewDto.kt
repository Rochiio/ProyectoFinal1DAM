package com.example.myanimelist.repositories.dto

import java.util.*

internal data class ReviewDto(
    val id: UUID = UUID.randomUUID(),
    val idAnime: UUID,
    val idUser: UUID,
    var score: Int,
    var comment: String
)