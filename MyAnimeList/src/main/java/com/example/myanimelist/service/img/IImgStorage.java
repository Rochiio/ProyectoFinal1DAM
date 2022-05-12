package com.example.myanimelist.service.img;

import com.example.myanimelist.models.User;
import com.example.myanimelist.service.IStorage;
import javafx.scene.image.Image;

public interface IImgStorage extends IStorage<User> {

    Image loadImg(User user);
}
