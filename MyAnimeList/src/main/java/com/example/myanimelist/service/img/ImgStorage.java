package com.example.myanimelist.service.img;

import com.example.myanimelist.managers.ResourcesManager;
import com.example.myanimelist.models.User;
import com.example.myanimelist.service.utils.Utils;
import com.example.myanimelist.utils.Properties;
import com.example.myanimelist.views.models.Presentation;
import javafx.scene.image.Image;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

    /**
     * @param file     file to copy
     * @param savePath path from where you want to save the file
     * @return the name of the file copied ready to be saved into the model
     */
    @Override
    public boolean cpFile(File file, String savePath) {
        String finalPath = savePath + File.separator + file.getName();
        return Utils.cp(file.getAbsolutePath(), finalPath);
    }

    @Override
    public Image loadImg(User user) {
        InputStream userImage = null;

        if (user.getImg() != null)
            userImage = ResourcesManager.INSTANCE.getUserImageOf(user.getImg());

        if (userImage != null) {
            logger.info("loading img");
            return new Image(userImage);
        }

        logger.info("loading default img");
        return new Image(Objects.requireNonNull(ResourcesManager.INSTANCE.getUserImageOf(Properties.DEFAULT_USER_ICON)));
    }

    @Override
    public Image loadImg(Presentation present) {
        InputStream animeImage = null;

        if (present.getImg() != null)
            animeImage = ResourcesManager.INSTANCE.getCoverOf(present.getImg());

        if (animeImage != null) {
            logger.info("loading img");
            return new Image(animeImage);
        }

        logger.info("loading default img");
        return new Image(Objects.requireNonNull(ResourcesManager.INSTANCE.getCoverOf(Properties.DEFAULT_IMAGE)));
    }
}
