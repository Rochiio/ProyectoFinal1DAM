package com.example.myanimelist.models

import java.util.*

data class Review(
    val anime: Anime,
    val user: User,
    var score: Int,
    var comment: String,
    val id: UUID = UUID.randomUUID()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Review

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}