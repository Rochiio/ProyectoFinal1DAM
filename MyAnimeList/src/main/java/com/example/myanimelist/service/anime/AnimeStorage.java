package com.example.myanimelist.service.anime;

import com.example.myanimelist.dto.AnimeDTO;
import com.example.myanimelist.utils.Properties;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author JoaquinAyG
 */

public class AnimeStorage implements IAnimeStorage {

    public AnimeStorage() {
        mkdir();
    }

    @Override
    public void mkdir() {
        Path dir = Path.of(Properties.CSV_DIR);
        if (Files.exists(dir)) return;

        try {
            Files.createDirectory(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void save(List<AnimeDTO> dtoList) {
        String header = "id,title,titleEnglish,types,episodes,status,date,rating,genres,img".replace(',', Properties.CSV_SEPARATOR);
        StringBuilder csv = new StringBuilder(header);
        var csvList = dtoList.stream().map(this::toCSV).toList();
        for (String s : csvList) {
            csv.append(s);
        }
        try (FileWriter writer = new FileWriter(Properties.ANIME_SAVE)) {
            writer.write(csv.toString());
        } catch (Exception e) {
            System.out.println("Error writing the file");
        }
    }

    @Override
    public Optional<List<AnimeDTO>> load() {
        try (var list = Files.lines(Path.of(Properties.ANIME_CSV)).skip(1)) {
            var animeList = list.map(this::parse).toList();
            return Optional.of(animeList);
        } catch (Exception e) {
            System.out.println("Error reading the file");
        }
        return Optional.empty();
    }

    private AnimeDTO parse(String line) {
        String[] fields = line.split(String.valueOf(Properties.CSV_SEPARATOR));
        UUID id = UUID.fromString(fields[0]);
        String title = fields[1];
        String titleEnglish = fields[2];
        String types = fields[3];
        int episodes = (int) Double.parseDouble(fields[4]);
        String status = fields[5];
        String rating = fields[6];
        String genres = fields[8];
        String date = fields[9];
        return new AnimeDTO(id, title, titleEnglish, types, episodes, status, date, rating, genres, id.toString());
    }

    private String toCSV(AnimeDTO animeDTO) {

        return "\n" +
                animeDTO.getId() + Properties.CSV_SEPARATOR +
                animeDTO.getTitle() + Properties.CSV_SEPARATOR +
                animeDTO.getTitleEnglish() + Properties.CSV_SEPARATOR +
                animeDTO.getTypes() + Properties.CSV_SEPARATOR +
                animeDTO.getEpisodes() + Properties.CSV_SEPARATOR +
                animeDTO.getStatus() + Properties.CSV_SEPARATOR +
                animeDTO.getDate() + Properties.CSV_SEPARATOR +
                animeDTO.getRating() + Properties.CSV_SEPARATOR +
                animeDTO.getGenres() + Properties.CSV_SEPARATOR +
                animeDTO.getImg();
    }
}
