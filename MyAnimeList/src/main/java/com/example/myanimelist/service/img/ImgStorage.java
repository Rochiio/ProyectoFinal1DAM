package com.example.myanimelist.service.img;

import com.example.myanimelist.models.User;
import com.example.myanimelist.utils.Properties;
import com.example.myanimelist.managers.ResourcesManager;
import com.example.myanimelist.views.models.Presentation;
import javafx.scene.image.Image;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ImgStorage implements IImgStorage {

    private final Logger logger;
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
    public Image loadImg(User user) {
        if (!Objects.requireNonNull(user.getImg()).isBlank() && ResourcesManager.INSTANCE.getImageOf(user.getImg()) != null) {
            logger.info("loading img");
            return new Image(Objects.requireNonNull(ResourcesManager.INSTANCE.getImageOf(user.getImg())));
        }
        logger.info("loading default img");
        return new Image(Objects.requireNonNull(ResourcesManager.INSTANCE.getIconOf(Properties.DEFAULT_USER_ICON)));
    }

    @Override
    public Image loadImg(Presentation present) {
        if (!Objects.requireNonNull(present.getImg()).isBlank() && ResourcesManager.INSTANCE.getCoverOf(present.getImg()) != null) {
            logger.info("loading img");
            return new Image(Objects.requireNonNull(ResourcesManager.INSTANCE.getCoverOf(present.getImg())));
        }
        logger.info("loading default img");
        return new Image(Objects.requireNonNull(ResourcesManager.INSTANCE.getIconOf(Properties.DEFAULT_IMAGE)));
    }
}
