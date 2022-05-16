package com.example.myanimelist.controllers;

import com.example.myanimelist.extensions.AlertExtensionsKt;
import com.example.myanimelist.managers.DependenciesManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AcercaDeController {
    private final Desktop desktop = Desktop.getDesktop();
    private final Logger logger = DependenciesManager.getLogger(AcercaDeController.class);

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
