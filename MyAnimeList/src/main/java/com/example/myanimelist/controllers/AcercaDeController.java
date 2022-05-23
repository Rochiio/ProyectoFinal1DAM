package com.example.myanimelist.controllers;

import com.example.myanimelist.MyAnimeListApplication;
import com.example.myanimelist.extensions.AlertExtensionsKt;
import com.example.myanimelist.managers.DependenciesManager;
import com.example.myanimelist.utils.ThemesManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AcercaDeController {
    private final Desktop desktop = Desktop.getDesktop();
    private final Logger logger = DependenciesManager.getLogger(AcercaDeController.class);

    @FXML
    public Pane root;

    @FXML
    public void initialize() {
        root.getStylesheets().clear();
        root.getStylesheets().add(MyAnimeListApplication.class.getResource(ThemesManager.INSTANCE.getCurretnTheme().getValue()).toString());
    }

    @FXML
    private void linkGitHub(ActionEvent event) {
        var node = (Node) event.getSource();
        openPage(node.getUserData().toString());
    }

    private void openPage(String page) {
        try {
            desktop.browse(new URI(page));
        } catch (IOException | URISyntaxException ex) {
            logger.error("error al abrir " + page, ex);
            AlertExtensionsKt.show(new Alert(Alert.AlertType.ERROR),
                    "Error al abrir la página",
                    "Contacte con el administrador");
        }
    }

}
