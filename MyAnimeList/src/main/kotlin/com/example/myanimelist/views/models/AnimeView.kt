package com.example.myanimelist.views.models

import com.example.myanimelist.models.Anime
import javafx.beans.property.*
import java.time.LocalDate
import java.util.*

class AnimeView {
    val presentation: ObjectProperty<Presentation>
    val types: StringProperty
    val episodes: IntegerProperty
    val status: StringProperty
    val date: ObjectProperty<LocalDate>
    val rating: StringProperty
    val genres: StringProperty
    val id: UUID
    var ranking: IntegerProperty = SimpleIntegerProperty(0)

    constructor(
        title: String?,
        titleEnglish: String?,
        types: String?,
        episodes: Int,
        status: String?,
        date: LocalDate,
        rating: String?,
        genres: String?,
        id: UUID
    ) {
        presentation = SimpleObjectProperty(Presentation(title, titleEnglish, "$id.jpg"))
        this.types = SimpleStringProperty(types)
        this.episodes = SimpleIntegerProperty(episodes)
        this.status = SimpleStringProperty(status)
        this.date = SimpleObjectProperty(date)
        this.rating = SimpleStringProperty(rating)
        this.genres = SimpleStringProperty(genres)
        this.id = id
    }

    constructor(anime: Anime) {
        presentation = SimpleObjectProperty(Presentation(anime.title, anime.titleEnglish, "${anime.id}.jpg"))
        types = SimpleStringProperty(anime.types)
        episodes = SimpleIntegerProperty(anime.episodes)
        status = SimpleStringProperty(anime.status)
        date = SimpleObjectProperty(anime.date)
        rating = SimpleStringProperty(anime.rating)
        genres = SimpleStringProperty(anime.genres.joinToString(","))
        id = anime.id
    }

    fun toPOJO(): Anime {
        return Anime(
            presentation.get().getTitle(),
            presentation.get().getTitleEnglish(),
            getTypes(),
            getEpisodes(),
            getStatus(),
            getDate(),
            getRating(),
            getGenres().split(","),
            presentation.get().getImg(),
            id
        )
    }

    fun getRanking(): Int {
        return ranking.get()
    }

    fun setRanking(ranking: Int) {
        this.ranking.set(ranking)
    }

    fun rankingProperty(): IntegerProperty {
        return ranking
    }

    fun getPresentation(): Presentation {
        return presentation.get()
    }

    fun setPresentation(presentation: Presentation) {
        this.presentation.set(presentation)
    }

    fun presentationProperty(): ObjectProperty<Presentation> {
        return presentation
    }

    fun getTypes(): String {
        return types.get()
    }

    fun setTypes(types: String) {
        this.types.set(types)
    }

    fun typesProperty(): StringProperty {
        return types
    }

    fun getEpisodes(): Int {
        return episodes.get()
    }

    fun setEpisodes(episodes: Int) {
        this.episodes.set(episodes)
    }

    fun episodesProperty(): IntegerProperty {
        return episodes
    }

    fun getStatus(): String {
        return status.get()
    }

    fun setStatus(status: String) {
        this.status.set(status)
    }

    fun statusProperty(): StringProperty {
        return status
    }

    fun getDate(): LocalDate {
        return date.get()
    }

    fun setDate(date: LocalDate) {
        this.date.set(date)
    }

    fun dateProperty(): ObjectProperty<LocalDate> {
        return date
    }

    fun getRating(): String {
        return rating.get()
    }

    fun setRating(rating: String) {
        this.rating.set(rating)
    }

    fun ratingProperty(): StringProperty {
        return rating
    }

    fun getGenres(): String {
        return genres.get()
    }

    fun setGenres(genres: String) {
        this.genres.set(genres)
    }

    fun genresProperty(): StringProperty {
        return genres
    }

    override fun toString(): String {
        return "AnimeView{" +
                "title=" + presentation.get().title +
                ", titleEnglish=" + presentation.get().titleEnglish +
                ", types=" + types +
                ", episodes=" + episodes +
                ", status=" + status +
                ", date=" + date +
                ", rating=" + rating +
                ", genres=" + genres +
                ", img=" + presentation.get().img +
                ", id=" + id +
                '}'
    }
}