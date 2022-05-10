package com.example.myanimelistjava.utils;

import java.io.File;

public class Properties {

    //Misc
    public static final String CSV_SEPARATOR = ",";

    //Directories
    public static final String PATH = System.getProperty("user.dir");
    public static final String DATA_DIR = PATH + File.separator + "data";
    public static final String CSV_DIR = DATA_DIR + File.separator + "csv";
    public static final String JSON_DIR = DATA_DIR + File.separator + "json";
    public static final String IMG_DIR = DATA_DIR + File.separator + "img";
    public static final String ANIME_SAVE = CSV_DIR + File.separator + "anime_save.csv";
    public static final String ANIME_LOAD = CSV_DIR + File.separator + "anime_load.csv";
    public static final String JSON_FILE = JSON_DIR + File.separator + "backup.json";
}
