module com.example.myanimelistjava {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.mybatis;
    requires lombok;
    requires java.sql;
    requires org.apache.logging.log4j;
    requires com.google.gson;

    opens com.example.myanimelistjava to javafx.fxml;
    opens com.example.myanimelistjava.controllers to javafx.fxml;
    exports com.example.myanimelistjava;
}