package com.example.myanimelist.controllers;

import com.example.myanimelist.extensions.AlertExtensionsKt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import kotlin.Lazy;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.koin.java.KoinJavaComponent.inject;

public class AcercaDeController {
    private final Desktop desktop = Desktop.getDesktop();
    private final Lazy<Logger> logger = inject(Logger.class);

    @FXML
    private void linkGitHub(ActionEvent event) {
        var node = (Node) event.getSource();
        openPage(node.getUserData().toString());
    }

    private void openPage(String page) {
        try {
            desktop.browse(new URI(page));
        } catch (IOException | URISyntaxException ex) {
            logger.getValue().error("error al abrir " + page, ex);
            AlertExtensionsKt.show(new Alert(Alert.AlertType.ERROR),
                    "Error al abrir la página",
                    "Contacte con el administrador");
        }
    }

}
