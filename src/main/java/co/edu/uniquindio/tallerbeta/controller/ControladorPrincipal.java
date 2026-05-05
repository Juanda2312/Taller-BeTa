package co.edu.uniquindio.tallerbeta.controller;

import co.edu.uniquindio.tallerbeta.model.entity.Usuario;
import co.edu.uniquindio.tallerbeta.service.TallerServicio;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class ControladorPrincipal {

    private static ControladorPrincipal instancia;

    @Getter
    private final TallerServicio tallerServicio;

    @Getter
    @Setter
    private Usuario usuario;

    private ControladorPrincipal(){
        tallerServicio = new TallerServicio();
        usuario = null;
    }

    public static ControladorPrincipal getInstance(){
        if (instancia == null){
            instancia = new ControladorPrincipal();
        }
        return instancia;
    }


    public void cerrarVentana(Node node){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }


    public void crearAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
