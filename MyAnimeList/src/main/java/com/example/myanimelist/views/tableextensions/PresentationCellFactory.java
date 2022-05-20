package com.example.myanimelist.views.tableextensions;

import com.example.myanimelist.managers.DependenciesManager;
import com.example.myanimelist.service.img.IImgStorage;
import com.example.myanimelist.views.models.AnimeView;
import com.example.myanimelist.views.models.Presentation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PresentationCellFactory extends TableCell<AnimeView, Presentation> {

    IImgStorage imgStorage = DependenciesManager.getImgStorage();

    @Override
    public void updateItem(Presentation item, boolean empty) {
        if (empty) {
            setGraphic(null);
            return;
        }
        var vbox = new VBox();
        vbox.setSpacing(20.0);
        vbox.setAlignment(Pos.CENTER_LEFT);
        var hbox = new HBox();
        hbox.setSpacing(10.0);
        hbox.getChildren().add(new Label(item.getTitle()));
        hbox.getChildren().add(new Label(item.getTitleEnglish()));
        var imageview = new ImageView();
        imageview.setFitHeight(40.0);
        imageview.setFitWidth(40.0);
        imageview.setImage(imgStorage.loadImg(item));
        vbox.getChildren().add(imageview);
        vbox.getChildren().add(hbox);
        setGraphic(vbox);
    }

}
