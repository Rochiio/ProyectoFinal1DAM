package com.example.myanimelist.service.img

import com.example.myanimelist.models.User
import com.example.myanimelist.views.models.Presentation
import javafx.scene.image.Image
import java.io.File

interface IImgStorage {
    fun mkdir()
    fun cpFile(file: File, savePath: String?): Boolean
    fun loadImg(user: User): Image
    fun loadImg(present: Presentation): Image
}