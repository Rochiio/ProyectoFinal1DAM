package com.example.myanimelist.service.backup;

import com.example.myanimelist.dto.BackupDTO;
import com.example.myanimelist.utils.Properties;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BackupStorage implements IBackupStorage {

    BackupDTO bck;

    public BackupStorage(BackupDTO bck) {
        this.bck = bck;
        mkdir();
    }

    @Override
    public void save(BackupDTO dto) {
        var gson = new GsonBuilder().create();
        var json = gson.toJson(dto);
        try (FileWriter writer = new FileWriter(Properties.JSON_FILE)) {
            writer.write(json);
        } catch (Exception e) {
            System.out.println("Error writing the file");
        }
    }

    @Override
    public BackupDTO load() throws IOException {
        Gson gson = new GsonBuilder().create();
        BackupDTO root = null;
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(Properties.JSON_FILE));
            root = gson.fromJson(reader, (Type) BackupDTO[].class);
        } catch (Exception e) {
            System.out.println("Error reading the file");
        } finally {
            if (reader != null) reader.close();
        }
        return root;
    }
}
