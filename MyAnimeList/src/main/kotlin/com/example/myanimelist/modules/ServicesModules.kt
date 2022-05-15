package com.example.myanimelist.modules

import com.example.myanimelist.service.anime.AnimeStorage
import com.example.myanimelist.service.anime.IAnimeStorage
import com.example.myanimelist.service.backup.BackupStorage
import com.example.myanimelist.service.backup.IBackupStorage
import org.koin.dsl.module

val servicesModules = module {
    single<IBackupStorage> { BackupStorage() }
    single<IAnimeStorage> { AnimeStorage() }
}