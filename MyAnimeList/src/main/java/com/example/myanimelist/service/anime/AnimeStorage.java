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
        var csvList = dtoList.stream().map(this::toCSV).collect(Collectors.toList());
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
        try {
            List<AnimeDTO> dtoList = Files.lines(Path.of(Properties.ANIME_LOAD))
                    .skip(0)
                    .map(this::parse)
                    .collect(Collectors.toList());
            return dtoList;
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
        StringBuilder csvLine = new StringBuilder();
        csvLine.append("\n")
                .append(animeDTO.getId())
                .append(animeDTO.getTitle())
                .append(animeDTO.getTitleEnglish())
                .append(animeDTO.getTypes())
                .append(animeDTO.getEpisodes())
                .append(animeDTO.getStatus())
                .append(animeDTO.getDate())
                .append(animeDTO.getRating())
                .append(animeDTO.getGenres())
                .append(animeDTO.getImg());

        return csvLine.toString();
    }
}
