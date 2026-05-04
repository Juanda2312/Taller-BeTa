module co.edu.uniquindio.tallerbeta {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.xml;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires java.desktop;

    opens co.edu.uniquindio.tallerbeta to javafx.fxml;
    opens co.edu.uniquindio.tallerbeta.model.entity to com.fasterxml.jackson.databind;
    opens co.edu.uniquindio.tallerbeta.model.Enum to com.fasterxml.jackson.databind;

    exports co.edu.uniquindio.tallerbeta;
}