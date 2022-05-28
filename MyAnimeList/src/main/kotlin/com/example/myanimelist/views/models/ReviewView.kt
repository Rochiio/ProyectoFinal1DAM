package com.example.myanimelist.views.models

import javafx.beans.property.IntegerProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import java.util.*

class ReviewView(score: Int, comment: String?, val id: UUID, val animeId: UUID, val userId: UUID) {
    val score: IntegerProperty
    val comment: StringProperty

    init {
        this.score = SimpleIntegerProperty(score)
        this.comment = SimpleStringProperty(comment)
    }

    fun getScore(): Int {
        return score.get()
    }

    fun setScore(score: Int) {
        this.score.set(score)
    }

    fun scoreProperty(): IntegerProperty {
        return score
    }

    fun getComment(): String {
        return comment.get()
    }

    fun setComment(comment: String) {
        this.comment.set(comment)
    }

    fun commentProperty(): StringProperty {
        return comment
    }
}