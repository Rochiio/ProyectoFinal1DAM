package com.example.myanimelist.service.img;

import com.example.myanimelist.models.User;
import com.example.myanimelist.service.utils.Utils;
import com.example.myanimelist.utils.Properties;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ImgStorage implements IImgStorage {

    public Logger logger = LogManager.getLogManager().getLogger("main_user.controller");
    public ImgStorage() {
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
}
