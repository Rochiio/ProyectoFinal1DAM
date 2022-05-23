package com.example.myanimelist.models.enums

import javafx.collections.FXCollections

enum class Status(val value: String = "Unknown") {
    FINISHED_AIRING("Finished Airing"),
    CURRENTLY_AIRING("Currently Airing"),
    NOT_YET_AIRED("Not yet aired");

    companion object {
        val sample = FXCollections.observableArrayList(Status.values().map { it.value }.toList())
    }
}
