package com.example.myanimelist.models.enums

import javafx.collections.FXCollections
import javafx.collections.ObservableList

enum class Type(val value: String = "Unknown") {
    TV("TV"),
    MOVIE("Movie"),
    MUSIC("Music"),
    OVA("OVA"),
    ONA("ONA"),
    SPECIAL("Special");

    companion object {
        val sample: ObservableList<String> = FXCollections.observableArrayList(Type.values().map { it.value }.toList())
    }
}
