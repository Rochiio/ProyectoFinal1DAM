package com.example.myanimelistjava.repositories.anime;

import com.example.myanimelistjava.managers.DataBaseManager;
import com.example.myanimelistjava.models.Anime;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public final class AnimeRepository implements IAnimeRepository {
    private final DataBaseManager db;

    public AnimeRepository(DataBaseManager databaseManager) {
        this.db = databaseManager;
    }

    public Anime findById(UUID id) throws SQLException {
        var query = "SELECT * FROM animes WHERE id = ?";
        try {
            db.open();
            var result = db.select(query, id.toString()).orElseThrow();

            if (!result.next()) return null;

            var anime = new Anime(
                    result.getString("title"),
                    result.getString("title_english"),
                    result.getString("type"),
                    result.getInt("episodes"),
                    result.getString("status"),
                    result.getDate("releaseDate"),
                    result.getString("rating"),
                    Arrays.stream(result.getString("genre").split(",")).toList(),
                    result.getString("imageUrl"),
                    UUID.fromString(result.getString("id"))
            );
            db.close();

            return anime;
        } catch (Exception e) {
            db.close();
        }
        return null;
    }

    public List<Anime> findAll() throws SQLException {
        var query = "SELECT * FROM animes";
        ArrayList<Anime> animes = new ArrayList<>();
        try {
            db.open();
            var result = db.select(query).orElseThrow();
            while (result.next()) {
                var anime = new Anime(
                        result.getString("title"),
                        result.getString("title_english"),
                        result.getString("type"),
                        result.getInt("episodes"),
                        result.getString("status"),
                        result.getDate("releaseDate"),
                        result.getString("rating"),
                        Arrays.stream(result.getString("genre").split(",")).toList(),
                        result.getString("imageUrl"),
                        UUID.fromString(result.getString("id"))
                );
                animes.add(anime);
            }
            db.close();
            return animes;
        } catch (SQLException ex) {
            db.close();
        }
        return null;
    }

    public Anime update(Anime item) throws SQLException {
        var query = "UPDATE animes SET " +
                "title = ?," +
                "title_english = ?," +
                "status = ?," +
                "genre = ?," +
                "releaseDate = ?," +
                "imageUrl = ?," +
                "episodes = ?," +
                "rating = ?," +
                "type = ?" +
                "WHERE id = ?";
        try {
            db.open();
            db.update(
                    query,
                    item.title,
                    item.titleEnglish,
                    item.status,
                    String.join(",", item.genres),
                    item.date,
                    item.img,
                    item.episodes,
                    item.rating,
                    item.types,
                    item.id.toString()
            );
            db.close();
            return item;

        } catch (Exception e) {
            db.close();
        }

        return null;
    }

    public Anime add(Anime item) throws SQLException {
        var query = "INSERT INTO animes VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
        try {
            db.open();
            db.insert(
                    query,
                    item.id,
                    item.title,
                    item.titleEnglish,
                    item.status,
                    String.join(",", item.genres),
                    item.date,
                    item.img,
                    item.episodes,
                    item.rating,
                    item.types
            );
            db.close();
            return item;

        } catch (Exception e) {
            db.close();
        }
        return null;
    }

    public void delete(UUID id) throws SQLException {
        var query = "DELETE FROM animes WHERE id = ?";
        try {
            db.open();
            db.delete(query, id);
            db.close();
        } catch (Exception e) {
            db.close();
        }

    }
}
