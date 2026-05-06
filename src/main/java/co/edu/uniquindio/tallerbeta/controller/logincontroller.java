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
            cambiarEscena("orden.fxml", "Taller BeTa — Órdenes", btniniciarsesion, 900, 600);
        } catch (Exception e) {
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void Registrarse(ActionEvent event) {
        cambiarEscena("registro.fxml", "Taller BeTa — Registro", btnregistrarse, 600, 540);
    }

    // Reutiliza el mismo Stage en vez de crear uno nuevo — así no se pierde el botón de maximizar
    private void cambiarEscena(String fxml, String titulo, Button origen, double ancho, double alto) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/tallerbeta/" + fxml));
            Parent root = loader.load();
            Stage stage = (Stage) origen.getScene().getWindow();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root, ancho, alto));
            stage.setMinWidth(ancho);
            stage.setMinHeight(alto);
            stage.setWidth(ancho);
            stage.setHeight(alto);
        } catch (Exception e) {
            controladorPrincipal.crearAlerta("Error al abrir ventana: " + e.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }
}