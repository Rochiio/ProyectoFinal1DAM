package com.example.myanimelist.service.anime;

import com.example.myanimelist.dto.AnimeDTO;
import com.example.myanimelist.utils.Properties;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author JoaquinAyG
 */

public class AnimeStorage implements IAimeStorage {

    public AnimeStorage() {
        mkdir();
    }

    @Override
    public void save(List<AnimeDTO> dtoList) {
        String header = "id,title,titleEnglish,types,episodes,status,date,rating,genres,img";
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
    public List<AnimeDTO> load() {
        try (var list = Files.lines(Path.of(Properties.ANIME_LOAD))) {
            return list.skip(1)
                    .map(this::parse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error reading th file");
        }
        return null;
    }

    private AnimeDTO parse(String line) {
        String[] fields = line.split(Properties.CSV_SEPARATOR);
        UUID id = UUID.fromString(fields[0]);
        String title = fields[1];
        String titleEnglish = fields[2];
        String types = fields[3];
        Integer episodes = Integer.parseInt(fields[4]);
        String status = fields[5];
        String date = fields[6];
        String rating = fields[7];
        String genres = fields[8];
        String img = fields[9];
        return new AnimeDTO(id, title, titleEnglish, types, episodes, status, date, rating, genres, img);
    }

    private String toCSV(AnimeDTO animeDTO) {

        return "\n" +
                animeDTO.getId() +
                animeDTO.getTitle() +
                animeDTO.getTitleEnglish() +
                animeDTO.getTypes() +
                animeDTO.getEpisodes() +
                animeDTO.getStatus() +
                animeDTO.getDate() +
                animeDTO.getRating() +
                animeDTO.getGenres() +
                animeDTO.getImg();
    }
}
