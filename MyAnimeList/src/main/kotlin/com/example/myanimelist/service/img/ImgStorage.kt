package com.example.myanimelist.service.img

import com.example.myanimelist.extensions.getLogger
import com.example.myanimelist.managers.ResourcesManager.getCoverOf
import com.example.myanimelist.managers.ResourcesManager.getUserImageOf
import com.example.myanimelist.models.User
import com.example.myanimelist.service.utils.Utils
import com.example.myanimelist.utils.Properties
import com.example.myanimelist.views.models.Presentation
import javafx.scene.image.Image
import org.apache.logging.log4j.Logger
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import java.util.*

class ImgStorage() : IImgStorage {
    val logger: Logger = getLogger<ImgStorage>()


    init {
        mkdir()
    }

    override fun mkdir() {
        val dir = Path.of(Properties.IMG_DIR)
        if (!Files.exists(dir)) {
            try {
                Files.createDirectory(dir)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * @param file     file to copy
     * @param savePath path from where you want to save the file
     * @return the name of the file copied ready to be saved into the model
     */
    override fun cpFile(file: File, savePath: String?): Boolean {
        val finalPath = savePath + File.separator + file.name
        return Utils.cp(file.absolutePath, finalPath)
    }

    override fun loadImg(user: User): Image {
        var userImage: InputStream? = null
        if (user.img != null) userImage = getUserImageOf(user.img)
        if (userImage != null) {
            logger.info("loading img")
            return Image(userImage)
        }
        logger.info("loading default img")
        return Image(Objects.requireNonNull(getUserImageOf(Properties.DEFAULT_USER_ICON)))
    }

    override fun loadImg(present: Presentation): Image {
        val animeImage: InputStream? = getCoverOf(present.getImg())
        if (animeImage != null) {
            logger.info("loading img")
            return Image(animeImage)
        }
        logger.info("loading default img")
        return Image(Objects.requireNonNull(getCoverOf(Properties.DEFAULT_IMAGE)))
    }
}