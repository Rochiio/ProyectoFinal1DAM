package com.example.myanimelist.models.enums

import javafx.collections.FXCollections
import javafx.collections.ObservableList

enum class Status(val value: String = "Unknown") {
    FINISHED_AIRING("Finished Airing"),
    CURRENTLY_AIRING("Currently Airing"),
    NOT_YET_AIRED("Not yet aired");

    companion object {
        val sample: ObservableList<String> =
            FXCollections.observableArrayList(Status.values().map { it.value }.toList())
    }
}
