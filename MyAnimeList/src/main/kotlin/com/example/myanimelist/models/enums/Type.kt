package com.example.myanimelist.models.enums

import javafx.collections.FXCollections

enum class Type(val value: String = "Unknown") {
    TV("TV"),
    MOVIE("Movie"),
    MUSIC("Music"),
    OVA("OVA"),
    ONA("ONA"),
    SPECIAL("Special");

    companion object {
        val sample = FXCollections.observableArrayList(Type.values().map { it.value }.toList())
    }
}
