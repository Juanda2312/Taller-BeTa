module co.edu.uniquindio.tallerbeta {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.tallerbeta to javafx.fxml;
    exports co.edu.uniquindio.tallerbeta;
}