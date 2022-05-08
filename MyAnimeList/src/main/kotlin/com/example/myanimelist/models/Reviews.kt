package com.example.myanimelist.models

import java.util.*

data class Reviews(

    val id: UUID = UUID.randomUUID(),
    val anime: Anime,
    val user: User,
    var score: Int,
    var comment: String

)