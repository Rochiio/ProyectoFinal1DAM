module com.example.myanimelist {
    opens com.example.myanimelist to javafx.fxml;
    opens com.example.myanimelist.controllers to javafx.fxml;
    opens com.example.myanimelist.controllers.inicio to javafx.fxml;
    opens com.example.myanimelist.di.components to javafx.fxml;
    opens com.example.myanimelist.di.modules to javafx.fxml;
    opens com.example.myanimelist.filters.login to javafx.fxml;

    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.mybatis;
    requires java.base;
    requires com.google.gson;
    requires org.apache.logging.log4j;

    requires java.compiler;
    requires java.desktop;
    requires com.google.errorprone.annotations;



    exports com.example.myanimelist;
    exports com.example.myanimelist.controllers;
    exports com.example.myanimelist.controllers.inicio;
    exports com.example.myanimelist.repositories;
    exports com.example.myanimelist.manager;
    exports com.example.myanimelist.filters.login;
    exports com.example.myanimelist.di.modules;
}