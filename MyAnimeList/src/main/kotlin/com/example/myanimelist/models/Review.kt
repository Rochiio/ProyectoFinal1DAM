package com.example.myanimelist.models

import java.util.*

data class Review(
    val anime: Anime,
    val user: User,
    var score: Int,
    var comment: String,
    val id: UUID = UUID.randomUUID()
)