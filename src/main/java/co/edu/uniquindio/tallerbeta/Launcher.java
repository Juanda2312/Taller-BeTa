package co.edu.uniquindio.tallerbeta;

import javafx.application.Application;

/**
 * Clase lanzadora de la aplicación JavaFX.
 * Proporciona el punto de entrada principal para ejecutar la aplicación.
 */
public class Launcher {
    /**
     * Método main que inicia la aplicación llamando a Application.launch.
     *
     * @param args Argumentos de línea de comandos (no utilizados en esta aplicación).
     */
    public static void main(String[] args) {
        Application.launch(HelloApplication.class, args);
    }
}
