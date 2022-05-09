package com.example.myanimelist.services

import com.example.myanimelist.modules.RepositoriesModules.repositoryModule
import com.example.myanimelist.service.backup.IBackupStorage
import org.junit.Test
import org.junit.jupiter.api.Order
import org.koin.core.context.startKoin
import org.koin.test.inject
import org.koin.test.junit5.AutoCloseKoinTest

class BackupStorageTest : AutoCloseKoinTest() {
    val storage: IBackupStorage by inject()

    init {
        startKoin { modules(repositoryModule) }
    }

    @Test
    @Order(0)
    fun save() {
        
    }

    @Test
    @Order(1)
    fun load() {
        storage.load()
    }


}