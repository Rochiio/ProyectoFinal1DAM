package com.example.myanimelistjava.service.backup;

import com.example.myanimelistjava.dto.BackupDTO;
import com.example.myanimelistjava.service.IStorage;
import com.example.myanimelistjava.dto.BackupDTO;
import com.example.myanimelistjava.service.IStorage;

public interface IBackupStorage extends IStorage<BackupDTO> {
    void mkdir();

    void save(BackupDTO dto);

    BackupDTO load();
}
