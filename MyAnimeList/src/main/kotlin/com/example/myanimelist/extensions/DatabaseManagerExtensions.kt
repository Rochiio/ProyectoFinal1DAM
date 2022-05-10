package com.example.myanimelist.extensions

import com.example.myanimelistjava.managers.DataBaseManager

//TODO add logger to log all db errors
inline fun _root_ide_package_.com.example.myanimelistjava.managers.DataBaseManager.execute(action: () -> Unit) {
    try {
        this.open()
        action()
    } catch (ex: Exception) {
        println(ex)
    } finally {
        this.close()
    }
}