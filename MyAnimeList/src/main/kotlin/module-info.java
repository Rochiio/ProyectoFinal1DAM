module com.example.myanimelist {
    opens com.example.myanimelist to javafx.fxml;
    opens com.example.myanimelist.controllers to javafx.fxml;
    opens com.example.myanimelist.controllers.inicio to javafx.fxml;
    //opens com.example.myanimelist.controllers.main to javafx.fxml;
    opens com.example.myanimelist.filters.login to javafx.fxml;
    opens com.example.myanimelist.dto to com.google.gson;
    opens com.example.myanimelist.models to com.google.gson;
    opens com.example.myanimelist.controllers.main.user to javafx.fxml;
    opens com.example.myanimelist.controllers.profiles to javafx.fxml;


    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.base;
    requires com.google.gson;
    requires org.apache.logging.log4j;

    requires java.compiler;
    requires java.desktop;
    requires org.mybatis;

    requires koin.core.jvm;


    exports com.example.myanimelist.managers;
    exports com.example.myanimelist;
    exports com.example.myanimelist.controllers;
    exports com.example.myanimelist.controllers.inicio;
    //exports com.example.myanimelist.controllers.main;
    exports com.example.myanimelist.controllers.main.admin;
    exports com.example.myanimelist.controllers.anime;
    exports com.example.myanimelist.controllers.main.user;
    exports com.example.myanimelist.repositories;
    exports com.example.myanimelist.filters.login;
    exports com.example.myanimelist.filters.edition;
    exports com.example.myanimelist.controllers.profiles;
}