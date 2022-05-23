package com.example.myanimelist.service.backup;

import com.example.myanimelist.dto.BackupDTO;
import com.example.myanimelist.utils.Properties;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * @author JoaquinAyG
 */
public class BackupStorage implements IBackupStorage {

    private final Gson gson;


    public BackupStorage(Gson gson) {
        mkdir();
        this.gson = gson;
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
    public void save(BackupDTO dto) {
        try (var writer = new FileWriter(Properties.JSON_FILE)) {
            gson.toJson(dto, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<BackupDTO> load() {
        try (var reader = new FileReader(Properties.JSON_FILE)) {
            return Optional.of(gson.fromJson(reader, BackupDTO.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
