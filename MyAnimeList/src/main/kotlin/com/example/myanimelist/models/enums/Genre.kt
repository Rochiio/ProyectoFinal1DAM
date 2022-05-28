package com.example.myanimelist.models.enums

import javafx.collections.FXCollections
import javafx.collections.ObservableList


enum class Genre(val value: String = "Unknown") {
    ADVENTURE("Adventure"),
    ACTION("Action"),
    COMEDY("Comedy"),
    SLICE_OF_LIFE("Slice of life"),
    DRAMA("Drama"),
    FANTASY("Fantasy"),
    SUPERNATURAL("Supernatural"),
    MAGIC("Magic"),
    MYSTERY("Mystery"),
    HORROR("Horror"),
    PSYCHOLOGICAL("Psychological"),
    SCI_FI("Sci-Fi"),
    ROMANCE("Romance");

    companion object {
        val sample: ObservableList<String> =
            FXCollections.observableArrayList(Genre.values().map { it.value }.toList())
    }
}