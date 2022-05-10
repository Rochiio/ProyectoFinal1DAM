package com.example.myanimelistjava.service.backup;

import com.example.myanimelistjava.dto.BackupDTO;
import com.example.myanimelistjava.configurations.Directories;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author JoaquinAyG
 */
public class BackupStorage implements IBackupStorage {

    BackupDTO bck;

    public BackupStorage(BackupDTO bck) {
        this.bck = bck;
        mkdir();
    }

    @Override
    public void mkdir() {
        Path dir = Path.of(Directories.JSON_DIR);
        if (!Files.exists(dir)) {
            try {
                Files.createDirectory(dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void save(BackupDTO dto) {
        var gson = new GsonBuilder().create();
        var json = gson.toJson(dto);
        try (FileWriter writer = new FileWriter(Directories.JSON_FILE)) {
            writer.write(json);
        } catch (Exception e) {
            System.out.println("Error writing the file");
        }
    }

    @Override
    public BackupDTO load() {
        Gson gson = new GsonBuilder().create();
        BackupDTO root = null;
        try (Reader reader = Files.newBufferedReader(Paths.get(Directories.JSON_FILE))) {
            root = gson.fromJson(reader, (Type) BackupDTO[].class);
        } catch (Exception e) {
            System.out.println("Error reading the file");
        }
        return root;
    }
}
