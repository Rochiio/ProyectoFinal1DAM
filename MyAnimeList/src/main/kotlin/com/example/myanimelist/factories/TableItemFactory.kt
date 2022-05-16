package com.example.myanimelist.factories

import com.example.myanimelist.views.models.Presentation
import javafx.collections.ObservableList
import javafx.scene.control.ChoiceBox
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import java.nio.file.Path

class TableItemFactory {

    fun getAnimePresentation(item: Presentation): VBox {
        val vbox = VBox()
        vbox.spacing = 20.0
        val hbox = HBox()
        hbox.spacing = 10.0
        hbox.children.add(Label(item.title))
        hbox.children.add(Label(item.titleEnglish))
        val imageview = ImageView()
        imageview.fitHeight = 40.0
        imageview.fitWidth = 40.0
        val dirImage = Path.of(item.img);
        imageview.image = Image(dirImage.toUri().toString())
        vbox.children.addAll(imageview, vbox)
        return vbox
    }

    fun getMenu(): VBox{
        val vBox = VBox()
        TODO("un monton de codigo pa hacer el menu to wapo")
        return vBox
    }
}