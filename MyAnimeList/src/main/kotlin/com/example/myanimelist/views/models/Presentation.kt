package com.example.myanimelist.views.models

import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty

class Presentation(title: String?, titleEnglish: String?, img: String?) {
    val title: StringProperty
    val titleEnglish: StringProperty
    var img: StringProperty

    init {
        this.img = SimpleStringProperty(img)
        this.title = SimpleStringProperty(title)
        this.titleEnglish = SimpleStringProperty(titleEnglish)
    }

    fun getTitle(): String {
        return title.get()
    }

    fun setTitle(title: String) {
        this.title.set(title)
    }

    fun titleProperty(): StringProperty {
        return title
    }

    fun getTitleEnglish(): String {
        return titleEnglish.get()
    }

    fun setTitleEnglish(titleEnglish: String) {
        this.titleEnglish.set(titleEnglish)
    }

    fun titleEnglishProperty(): StringProperty {
        return titleEnglish
    }

    fun getImg(): String? {
        return img.get()
    }

    fun setImg(img: String) {
        this.img.set(img)
    }

    fun imgProperty(): StringProperty {
        return img
    }
}