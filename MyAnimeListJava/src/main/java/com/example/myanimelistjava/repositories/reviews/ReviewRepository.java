package com.example.myanimelistjava.repositories.reviews;

import com.example.myanimelistjava.managers.DataBaseManager;
import com.example.myanimelistjava.models.Review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ReviewRepository implements IRepositoryReview {
    DataBaseManager db;

    public ReviewRepository(DataBaseManager db) {
        this.db = db;
    }


    @Override
    public Review addReview(Review review) throws SQLException {
        var query = "INSERT INTO reviews VALUES(?,?,null,?,?)";
        db.open();
        ResultSet resultado = db.insert(query, review.user, review.anime, review.id, review.comment)
                .orElseThrow(() -> new SQLException("Error al añadir review"));
        db.close();
        return (Review) resultado;
    }


    @Override
    public List<Review> findAllByAnimeId(UUID animeId) throws SQLException {
        var list= new ArrayList<Review>();
        String sql = "SELECT * FROM reviews WHERE idAnime = ?";
        db.open();
            var res = db.select(sql, animeId.toString()).orElseThrow(() -> new SQLException("Error al buscar animes"));
            while (res.next()) {
                var review = new Review(
                        UUID.fromString(res.getString("id")),
                        UUID.fromString(res.getString("idAnime")),
                        UUID.fromString(res.getString("idUser")),
                        res.getInt("score"),
                        res.getString("review")
                );
                list.add(review);
            }
            db.close();
        return list;
    }

    @Override
    public Review addScore(Review review) throws SQLException {
        var query = "INSERT INTO reviews VALUES(?,?,?,?,null)";
        db.open();
        ResultSet resultado = db.insert(query, review.user, review.anime,review.score, review.id)
                .orElseThrow(() -> new SQLException("Error al añadir score"));
        db.close();
        return (Review) resultado;
    }
}
