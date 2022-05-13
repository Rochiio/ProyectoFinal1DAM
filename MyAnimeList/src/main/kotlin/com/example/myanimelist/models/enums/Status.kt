package com.example.myanimelist.models.enums

enum class Status(val value: String = "Unknown") {
    FINISHED_AIRING("Finished Airing"),
    CURRENTLY_AIRING("Currently Airing"),
    NOT_YET_AIRED("Not yet aired")
}