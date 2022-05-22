package com.example.myanimelist.service.utils;

import com.example.myanimelist.managers.DependenciesManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Optional;

public class Utils {

    static Logger logger = DependenciesManager.getLogger(Utils.class);
    public static Optional<String> getFileExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public static Boolean cp(String from, String to) {
        logger.info("Copy files from " + from + " to " + to);
        Path initial = Path.of(from);
        if (!Files.exists(initial)) {
            logger.warn("Non existing file");
            return false;
        }
        try {
            Files.copy(initial, Paths.get(to), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            logger.warn("Error copying");
            System.out.println();
            return false;
        }
        return true;
    }

    public boolean deleteFile(String file) {
        logger.info("Deleting " + file);
        Path path = Path.of(file);
        if (!Files.exists(path)) {
            logger.warn("File not found");
            return false;
        }
        try {
            Files.delete(path);
        } catch (IOException e) {
            logger.warn("Error deleting");
            return false;
        }
        return true;
    }

    public static LocalDate parseLocalDate(String date){
        var fecha = date.split(" ");
       return LocalDate.of(Integer.parseInt(fecha[2]),getMonth(fecha[0]),Integer.parseInt(fecha[1]));
    }

    private static Month getMonth(String value) {
        Month result = null;
        switch (value) {
            case "Jan" -> result = Month.JANUARY;
            case "Feb" -> result = Month.FEBRUARY;
            case "Mar" -> result = Month.MARCH;
            case "Apr" -> result = Month.APRIL;
            case "May" -> result = Month.MAY;
            case "Jun" -> result = Month.JUNE;
            case "Jul" -> result = Month.JULY;
            case "Aug" -> result = Month.AUGUST;
            case "Sep" -> result = Month.SEPTEMBER;
            case "Oct" -> result = Month.OCTOBER;
            case "Nov" -> result = Month.NOVEMBER;
            case "Dec" -> result = Month.DECEMBER;
        }
        return result;
    }
}
