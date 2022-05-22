package com.example.myanimelist.repositories

import com.example.myanimelist.extensions.execute
import com.example.myanimelist.managers.DependenciesManager
import com.example.myanimelist.utils.Properties
import org.junit.jupiter.api.BeforeEach

open class RepoTest {
    @BeforeEach
    fun deleteAll() {
        DependenciesManager.isTesting = true
        DependenciesManager.getDatabaseManager().execute {
            initData(Properties.SCRIPT_FILE_DATABASE, false)
        }
    }
}