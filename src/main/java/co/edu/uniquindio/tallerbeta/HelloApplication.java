package co.edu.uniquindio.tallerbeta;

import co.edu.uniquindio.tallerbeta.util.DatosDePrueba;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        DatosDePrueba.cargar();

        FXMLLoader fxmlLoader = new FXMLLoader(
                HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 520);
        stage.setTitle("Taller BeTa — Registrar Orden de Mantenimiento");
        stage.setScene(scene);
        stage.setMinWidth(600);
        stage.setMinHeight(520);
        stage.show();
    }
}