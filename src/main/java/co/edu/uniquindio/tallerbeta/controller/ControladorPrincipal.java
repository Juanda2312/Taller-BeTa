package co.edu.uniquindio.tallerbeta.controller;

import co.edu.uniquindio.tallerbeta.model.entity.Usuario;
import co.edu.uniquindio.tallerbeta.service.TallerServicio;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

/**
 * Controlador principal singleton que gestiona el servicio del taller y el usuario actual.
 * Proporciona métodos utilitarios para cerrar ventanas y mostrar alertas.
 */
public class ControladorPrincipal {

    private static ControladorPrincipal instancia;

    @Getter
    private final TallerServicio tallerServicio;

    @Getter
    @Setter
    private Usuario usuario;

    /**
     * Constructor privado que inicializa el servicio del taller y el usuario como null.
     */
    private ControladorPrincipal(){
        tallerServicio = new TallerServicio();
        usuario = null;
    }

    /**
     * Método estático para obtener la instancia única del controlador principal.
     *
     * @return La instancia del controlador principal.
     */
    public static ControladorPrincipal getInstance(){
        if (instancia == null){
            instancia = new ControladorPrincipal();
        }
        return instancia;
    }

    /**
     * Cierra la ventana asociada al nodo proporcionado.
     *
     * @param node El nodo desde el cual obtener la ventana a cerrar.
     */
    public void cerrarVentana(Node node){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    /**
     * Crea y muestra una alerta con el mensaje y tipo especificados.
     *
     * @param mensaje El mensaje de la alerta.
     * @param tipo El tipo de alerta (ERROR, WARNING, INFORMATION, etc.).
     */
    public void crearAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
