module com.example.myanimelistjava {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.myanimelistjava to javafx.fxml;
    exports com.example.myanimelistjava;
}