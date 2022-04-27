module com.example.myanimelist {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.myanimelist to javafx.fxml;
    exports com.example.myanimelist;
}