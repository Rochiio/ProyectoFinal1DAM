package com.example.myanimelist.utils;

import java.io.File;

public class Properties {

    //Misc
    public static final char CSV_SEPARATOR = ';';

    //Directories
    public static final String PATH = System.getProperty("user.dir");
    public static final String DB_DIR = PATH + File.separator + "db";
    public static final String SCRIPT_FILE_DATABASE = DB_DIR + File.separator + "testsData.sql";
    public static final String DATA_DIR = PATH + File.separator + "data";
    public static final String CSV_DIR = DATA_DIR + File.separator + "csv";
    public static final String ANIME_SAVE = CSV_DIR + File.separator + "anime_save.csv";
    public static final String ANIME_CSV = CSV_DIR + File.separator + "animeCSVSmall.csv";
    public static final String JSON_DIR = DATA_DIR + File.separator + "json";
    public static final String JSON_FILE = JSON_DIR + File.separator + "backup.json";
    public static final String LOAD_FILE = JSON_DIR + File.separator + "load.json";
    public static final String IMG_DIR = DATA_DIR + File.separator + "img";
    public static final String SRC_DIR = PATH + File.separator + "src" + File.separator + "main";
    public static final String RESOURCES_DIR = SRC_DIR + File.separator + "resources";
    public static final String RESOURCES_IMAGES_DIR = RESOURCES_DIR + File.separator + "com" + File.separator + "example" + File.separator + "myanimelist" + File.separator + "images";
    public static final String USER_IMG_DIR = RESOURCES_IMAGES_DIR + File.separator + "user";
    public static final String COVERS_DIR = RESOURCES_DIR + File.separator + "com" + File.separator + "example" + File.separator + "myanimelist" + File.separator + "covers";
    public static final String ICONS_DIR = RESOURCES_DIR + File.separator + "icons";
    public static final String DEFAULT_USER_ICON = "default.png";
    public static final String ADD_ICON = "add.png";
    public static final String EDIT_ICON = "add.png";
    public static final String DEFAULT_IMAGE = "image.png";
}
