module com.example.biblioteka {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.mail;

    requires org.controlsfx.controls;

    opens com.example.biblioteka to javafx.fxml;
    exports com.example.biblioteka;
}