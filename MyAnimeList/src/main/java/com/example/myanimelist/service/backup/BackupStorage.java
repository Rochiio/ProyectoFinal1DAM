package com.example.myanimelist.service.backup;

import com.example.myanimelist.dto.BackupDTO;

import java.io.IOException;

public class BackupStorage implements IBackupStorage {

    BackupDTO bck;

    public BackupStorage(BackupDTO bck) {
        this.bck = bck;
    }

    @Override
    public void save(BackupDTO dto) throws IOException {
        
    }

    @Override
    public BackupDTO load() throws IOException {
        return null;
    }
}
