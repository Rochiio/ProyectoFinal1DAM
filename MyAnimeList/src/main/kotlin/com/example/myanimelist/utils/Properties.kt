package com.example.myanimelist.utils

import java.io.File

object Properties {
    //Misc
    const val CSV_SEPARATOR = ';'

    //Directories
    val PATH = System.getProperty("user.dir")
    val DB_DIR = PATH + File.separator + "db"
    val SCRIPT_FILE_DATABASE = DB_DIR + File.separator + "testsData.sql"
    val DATA_DIR = PATH + File.separator + "data"
    val CSV_DIR = DATA_DIR + File.separator + "csv"
    val ANIME_SAVE = CSV_DIR + File.separator + "anime_save.csv"
    val ANIME_CSV = CSV_DIR + File.separator + "animeCSVSmall.csv"
    val JSON_DIR = DATA_DIR + File.separator + "json"
    val JSON_FILE = JSON_DIR + File.separator + "backup.json"
    val LOAD_FILE = JSON_DIR + File.separator + "load.json"
    val IMG_DIR = DATA_DIR + File.separator + "img"
    val SRC_DIR = PATH + File.separator + "src" + File.separator + "main"
    val RESOURCES_DIR = SRC_DIR + File.separator + "resources"
    val RESOURCES_IMAGES_DIR =
        RESOURCES_DIR + File.separator + "com" + File.separator + "example" + File.separator + "myanimelist" + File.separator + "images"
    val USER_IMG_DIR = RESOURCES_IMAGES_DIR + File.separator + "user"
    val COVERS_DIR =
        RESOURCES_DIR + File.separator + "com" + File.separator + "example" + File.separator + "myanimelist" + File.separator + "covers"
    val ICONS_DIR = RESOURCES_DIR + File.separator + "icons"
    const val DEFAULT_USER_ICON = "default.png"
    const val ADD_ICON = "add.png"
    const val EDIT_ICON = "add.png"
    const val DEFAULT_IMAGE = "image.png"
}