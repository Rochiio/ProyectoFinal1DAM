package com.example.myanimelist.javafx.factories

import com.example.myanimelist.service.img.ImgStorage
import com.example.myanimelist.views.models.Presentation
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import java.nio.file.Path

class TableItemFactory {

    //val imgstorage: ImgStorage = DependenciesManager.getImgStorage
    private val imgStorage: ImgStorage = ImgStorage()
    fun getAnimePresentation(item: Presentation): VBox {
        val vbox = VBox()
        vbox.spacing = 20.0
        vbox.alignment = Pos.CENTER_LEFT
        val hbox = HBox()
        hbox.spacing = 10.0
        hbox.children.add(Label(item.title))
        hbox.children.add(Label(item.titleEnglish))
        val imageview = ImageView()
        imageview.fitHeight = 40.0
        imageview.fitWidth = 40.0
        imageview.image = imgStorage.loadImg(item)
        vbox.children.add(imageview)
        vbox.children.add(hbox)
        return vbox
    }

    fun getMenu(): VBox {
        val vBox = VBox()
        TODO("un monton de codigo pa hacer el menu to wapo")
        return vBox
    }
}