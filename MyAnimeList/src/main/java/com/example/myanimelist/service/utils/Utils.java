package com.example.myanimelist.service.utils;

import com.example.myanimelist.managers.DependenciesManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
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

    public static LocalDate parseLocalDate(String date) {
        var fecha = date.split("/");
        return LocalDate.of(Integer.parseInt(fecha[2]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[0]));
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

}
