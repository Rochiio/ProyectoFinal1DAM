module com.example.myanimelist {
    opens com.example.myanimelist to javafx.fxml;

    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.mybatis;
    requires lombok;

    exports com.example.myanimelist;
    exports com.example.myanimelist.controllers;
}