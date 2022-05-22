package com.example.myanimelist.extensions

import com.example.myanimelist.manager.DataBaseManager
import org.apache.logging.log4j.Logger

//TODO add logger to log all db errors

inline fun DataBaseManager.execute(logger: Logger? = null, action: DataBaseManager.() -> Unit) {
    try {
        this.open()
        action()
    }
    catch (ex: Exception) {
        logger?.error(ex) ?: println(ex)
    }
    finally {
        this.close()
    }
}