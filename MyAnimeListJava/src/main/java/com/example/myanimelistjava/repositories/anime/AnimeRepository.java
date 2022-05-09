package com.example.myanimelistjava.repositories.anime;

import com.example.myanimelistjava.managers.DataBaseManager;
import com.example.myanimelistjava.models.Anime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public final class AnimeRepository implements IAnimeRepository {
    private final DataBaseManager databaseManager;

    public AnimeRepository(DataBaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public Anime findById(UUID id) {
        var query = "SELECT * FROM animes WHERE id = ?";
        try {
            databaseManager.execute {
                var result = databaseManager.select(query, id.toString()).orElseThrow();

                if (!result.next()) return null;

                return new Anime(
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
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Anime> findAll() {
        var query = "SELECT * FROM animes";
        ArrayList animes = new ArrayList();
        try {
            databaseManager.execute {
                var result = databaseManager.select(query).orElseThrow();
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
                return animes;
            }
        } catch (Exception e){

        }
        return animes;
    }

    public Anime update(Anime item) {
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
            databaseManager.execute {
                databaseManager.update(
                        query,
                        item.title,
                        item.titleEnglish,
                        item.status,
                        item.genres.stream().collect(Collectors.joining(",")),
                        item.date,
                        item.img,
                        item.episodes,
                        item.rating,
                        item.types,
                        item.id.toString()
                );
                return item;
            }
        } catch (Exception e){

        }

        return null;
    }

    public Anime add(Anime item) {
        var query = "INSERT INTO animes VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
        try {
            databaseManager.execute {
                databaseManager.insert(
                        query,
                        item.id,
                        item.title,
                        item.titleEnglish,
                        item.status,
                        item.genres.stream().collect(Collectors.joining(",")),
                        item.date,
                        item.img,
                        item.episodes,
                        item.rating,
                        item.types
                );
                return item;
            }
        } catch (Exception e){

        }
        return null;
    }

    public void delete(UUID id) {
        var query = "DELETE FROM animes WHERE id = ?";
        try{
        databaseManager.execute {
            databaseManager.delete(query, id);
        }
        } catch (Exception e){

        }
    }
}
