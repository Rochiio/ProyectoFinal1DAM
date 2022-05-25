package com.example.myanimelist.service.txt;

import com.example.myanimelist.dto.LoadDTO;
import com.example.myanimelist.managers.DependenciesManager;
import com.example.myanimelist.utils.Properties;
import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class TxtBackup implements ITxtStorage {

    private final Gson gson = DependenciesManager.getGson();

    public TxtBackup(){
        mkdir();
    }

    @Override
    public void mkdir() {
        Path dir = Path.of(Properties.JSON_DIR);
        if (Files.exists(dir)) return;
        try {
            Files.createDirectory(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(LoadDTO dto) {
        try (var writer = new FileWriter(Properties.LOAD_FILE)) {
            gson.toJson(dto, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<LoadDTO> load() {
        try (var reader = new FileReader(Properties.LOAD_FILE)) {
            return Optional.of(gson.fromJson(reader, LoadDTO.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
