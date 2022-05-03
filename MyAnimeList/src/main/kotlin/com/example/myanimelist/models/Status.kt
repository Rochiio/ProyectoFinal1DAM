package com.example.myanimelist.models

enum class Status(value: String = "Unknown") {
    FINISHED_AIRING("Finished Airing"),
    CURRENTLY_AIRING("Currently Airing"),
    NOT_YET_AIRED("Not yet aired")
}