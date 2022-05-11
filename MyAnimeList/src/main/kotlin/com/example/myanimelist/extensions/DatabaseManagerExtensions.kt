package com.example.myanimelist.extensions

import com.example.myanimelist.manager.DataBaseManager

//TODO add logger to log all db errors
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