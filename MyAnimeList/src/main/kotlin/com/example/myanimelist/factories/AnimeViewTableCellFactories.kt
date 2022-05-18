package com.example.myanimelist.factories

import com.example.myanimelist.views.models.AnimeView
import com.example.myanimelist.views.models.Presentation
import javafx.collections.ObservableList
import javafx.scene.control.ChoiceBox
import javafx.scene.control.TableCell

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

class AnimeViewTableCellPresentation : TableCell<AnimeView, Presentation>() {
    override fun updateItem(item: Presentation, empty: Boolean) {
        graphic = TableItemFactory().getAnimePresentation(item)
    }
}