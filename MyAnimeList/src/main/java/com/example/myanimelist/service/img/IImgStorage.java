package com.example.myanimelist.service.img;

import com.example.myanimelist.models.User;
import com.example.myanimelist.views.models.Presentation;
import javafx.scene.image.Image;

public interface IImgStorage {

    void mkdir();

    Image loadImg(User user);

    Image loadImg(Presentation present);
}
