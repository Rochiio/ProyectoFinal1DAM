package com.example.myanimelist.service;

import com.example.myanimelist.utils.Properties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author JoaquinAyG
 * @param <T> DTO from object to save or load
 */
public interface IStorage<T> {

    default void mkdir() {
        Path root = Path.of(Properties.DATA_DIR);
        if (!Files.exists(root)) {
            try {
                Files.createDirectory(root);
                Files.createDirectory(Paths.get(Properties.JSON_DIR));
                Files.createDirectory(Paths.get(Properties.CSV_DIR));
                Files.createDirectory(Paths.get(Properties.IMG_DIR));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void save(List<T> dtoList)throws IOException;

    List<T> load()throws IOException, ClassNotFoundException;
}
