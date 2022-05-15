module com.example.myanimelist {
    opens com.example.myanimelist to javafx.fxml;
    opens com.example.myanimelist.controllers to javafx.fxml;
    opens com.example.myanimelist.controllers.inicio to javafx.fxml;
    opens com.example.myanimelist.dto to com.google.gson;
    opens com.example.myanimelist.models to com.google.gson;

    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.mybatis;
    requires lombok;
    requires java.base;
    requires koin.core.jvm;
    requires com.google.gson;
    requires org.apache.logging.log4j;
    requires java.desktop;

    exports com.example.myanimelist;
    exports com.example.myanimelist.controllers;
    exports com.example.myanimelist.dto;
    exports com.example.myanimelist.models;

}