package com.example.myanimelist.javafx.factories

import com.example.myanimelist.utils.Properties
import com.example.myanimelist.views.models.AnimeView
import javafx.collections.ObservableList
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.TableCell
import javafx.scene.image.Image
import javafx.scene.image.ImageView

class AnimeViewTableCell(private val enumSet: ObservableList<*>) : TableCell<AnimeView, String>() {
    override fun updateItem(item: String?, empty: Boolean) {
        val choiceBox: ChoiceBox<*> = ChoiceBox(enumSet)
        choiceBox.selectionModel.select(enumSet.indexOf(item))
        choiceBox.setOnAction {
            val selection = choiceBox.selectionModel.selectedItem as String
            val anime = tableView.items[index]
            anime.enumParser(selection)
        }
        graphic = choiceBox
    }
}