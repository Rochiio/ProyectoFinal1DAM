package com.example.myanimelist.extensions

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

inline fun <reified T> getLogger(): Logger =
    LogManager.getLogger(T::class.java)