package co.edu.uniquindio.tallerbeta;

import co.edu.uniquindio.tallerbeta.util.DatosDePrueba;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación JavaFX para el sistema de gestión del Taller BeTa.
 * Esta clase extiende Application y se encarga de inicializar la interfaz gráfica cargando la vista de login.
 */
public class HelloApplication extends Application {

    /**
     * Método principal que inicia la aplicación JavaFX.
     * Carga los datos de prueba, configura el escenario principal con la vista de login,
     * establece el título, tamaño mínimo y muestra la ventana.
     *
     * @param stage El escenario principal de la aplicación.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
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