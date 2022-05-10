package com.example.myanimelistjava.service.anime;

import com.example.myanimelistjava.dto.AnimeDTO;
import com.example.myanimelistjava.configurations.Directories;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author JoaquinAyG
 */

public class AnimeStorage implements IAimeStorage {

    public AnimeStorage() {
        mkdir();
    }

    @Override
    public void mkdir() {
        Path dir = Path.of(Directories.CSV_DIR);
        if (!Files.exists(dir)) {
            try {
                Files.createDirectory(dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void save(List<AnimeDTO> dtoList) {
        String[] headers = {"id",
                "title",
                "titleEnglish",
                "types",
                "episodes",
                "status",
                "date",
                "rating",
                "genres",
                "img"};
        String csvHeader = String.join(Directories.CSV_SEPARATOR, headers);
        var csvList = dtoList.stream().map(this::toCSV).toList();

        try (FileWriter writer = new FileWriter(Directories.ANIME_SAVE)) {
            writer.write(csvHeader);
            for (String line: csvList){
                writer.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error writing the file");
        }
    }

    @Override
    public List<AnimeDTO> load() {
        try (var list = Files.lines(Path.of(Directories.ANIME_LOAD))) {
            return list.skip(1)
                    .map(this::parse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error reading th file");
        }
        return null;
    }

    private AnimeDTO parse(String line) {
        String[] fields = line.split(Directories.CSV_SEPARATOR);
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

        String[] fields =  {animeDTO.getId().toString() ,
                animeDTO.getTitle() ,
                animeDTO.getTitleEnglish() ,
                animeDTO.getTypes() ,
                animeDTO.getEpisodes().toString() ,
                animeDTO.getStatus() ,
                animeDTO.getDate() ,
                animeDTO.getRating() ,
                animeDTO.getGenres() ,
                animeDTO.getImg()};
        return String.join(Directories.CSV_SEPARATOR, fields);
    }
}
