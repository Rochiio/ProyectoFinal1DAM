package com.example.myanimelist.service.img;

import com.example.myanimelist.models.User;
import com.example.myanimelist.service.utils.Utils;
import com.example.myanimelist.utils.Properties;
import com.example.myanimelist.views.models.Presentation;
import javafx.scene.image.Image;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class ImgStorage implements IImgStorage {

    private Logger logger;
    public ImgStorage(Logger logger) {
        this.logger = logger;
        mkdir();
    }

    @Override
    public void mkdir() {
        Path dir = Path.of(Properties.IMG_DIR);
        if (!Files.exists(dir)) {
            try {
                Files.createDirectory(dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void save(User user) {
        String to = Properties.IMG_DIR + File.separator + user.getId() + "." + Utils.getFileExtension(user.getImg()).orElse("png");
        String from = Objects.requireNonNull(user.getImg()).replace("file:", "");
        logger.info("from: " + from);
        logger.info("to: " + to);
        Utils.cp(from, to);
        user.setImg(to);
    }

    @Override
    public Image loadImg(User user) {
        if (!Objects.requireNonNull(user.getImg()).isBlank() && Files.exists(Paths.get(user.getImg()))) {
            logger.info("loading img");
            return new Image(new File(user.getImg()).toURI().toString());
        }
        logger.info("loading default img");
        user.setImg(Properties.DEFAULT_USER_ICON);
        return new Image(Properties.DEFAULT_USER_ICON);
    }

    @Override
    public Image loadImg(Presentation present) {
        if (!Objects.requireNonNull(present.getImg()).isBlank() && Files.exists(Paths.get(present.getImg()))) {
            logger.info("loading img");
            return new Image(new File(present.getImg()).toURI().toString());
        }
        logger.info("loading default img");
        present.setImg(Properties.DEFAULT_IMAGE);
        return new Image(Properties.DEFAULT_IMAGE);
    }
}
