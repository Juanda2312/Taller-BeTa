package co.edu.uniquindio.tallerbeta.controller;

import co.edu.uniquindio.tallerbeta.service.TallerServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class logincontroller {

    private final ControladorPrincipal controladorPrincipal;
    private final TallerServicio tallerServicio;

    @FXML private Button        btniniciarsesion;
    @FXML private Button        btnregistrarse;
    @FXML private PasswordField txtcontraseña;
    @FXML private TextField     txtcorreo;

    public logincontroller() {
        controladorPrincipal = ControladorPrincipal.getInstance();
        tallerServicio       = controladorPrincipal.getTallerServicio();
    }

    @FXML
    void iniciarSesion(ActionEvent event) {
        String correo = txtcorreo.getText().trim();
        String pass   = txtcontraseña.getText();

        if (correo.isEmpty() || pass.isEmpty()) {
            controladorPrincipal.crearAlerta("Complete todos los campos.", Alert.AlertType.WARNING);
            return;
        }

        try {
            tallerServicio.iniciarSesion(correo, pass);
            controladorPrincipal.setUsuario(tallerServicio.buscarClientePorCorreo(correo));
            abrirVentana("orden.fxml", "Solicitar Orden", btniniciarsesion);
        } catch (Exception e) {
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void Registrarse(ActionEvent event) {
        abrirVentana("registro.fxml", "Registro", btnregistrarse);
    }

    private void abrirVentana(String fxml, String titulo, Button origen) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/tallerbeta/" + fxml));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
            controladorPrincipal.cerrarVentana(origen);
        } catch (Exception e) {
            controladorPrincipal.crearAlerta("Error al abrir ventana: " + e.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }
}