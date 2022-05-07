package com.example.myanimelist.extensions

import com.example.myanimelist.managers.DataBaseManager

inline fun DataBaseManager.execute(action: () -> Unit) {
    try {
        this.open()
        action()
    } catch (ex: Exception) {
        println(ex)
    } finally {
        this.close()
    }
}