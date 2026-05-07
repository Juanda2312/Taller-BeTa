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

public class registrocontroller {

    private final ControladorPrincipal controladorPrincipal;
    private final TallerServicio tallerServicio;

    @FXML private Button        btnregistrarse;
    @FXML private Button        btnvolver;
    @FXML private TextField     txtcedula;
    @FXML private PasswordField txtconfirmarcontraseña;
    @FXML private PasswordField txtcontraseña;
    @FXML private TextField     txtcorreo;
    @FXML private TextField     txtnombre;

    public registrocontroller() {
        controladorPrincipal = ControladorPrincipal.getInstance();
        tallerServicio       = controladorPrincipal.getTallerServicio();
    }

    @FXML
    void registrarse(ActionEvent event) {
        String nombre    = txtnombre.getText().trim();
        String cedula    = txtcedula.getText().trim();
        String correo    = txtcorreo.getText().trim();
        String pass      = txtcontraseña.getText();
        String confirmar = txtconfirmarcontraseña.getText();

        if (nombre.isEmpty() || cedula.isEmpty() || correo.isEmpty()
                || pass.isEmpty() || confirmar.isEmpty()) {
            controladorPrincipal.crearAlerta("Todos los campos son obligatorios.", Alert.AlertType.WARNING);
            return;
        }
        if (!pass.equals(confirmar)) {
            controladorPrincipal.crearAlerta("Las contraseñas no coinciden.", Alert.AlertType.WARNING);
            return;
        }
        if (pass.length() < 6) {
            controladorPrincipal.crearAlerta("La contraseña debe tener mínimo 6 caracteres.", Alert.AlertType.WARNING);
            return;
        }
        try {
            tallerServicio.registrarCliente(nombre, cedula, correo, pass);
            controladorPrincipal.crearAlerta("Registro exitoso! Ya puede iniciar sesión.", Alert.AlertType.INFORMATION);
            volver(event);
        } catch (Exception e) {
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void volver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/tallerbeta/login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnvolver.getScene().getWindow();
            boolean estabaMaximizado = stage.isMaximized();
            stage.setTitle("Taller BeTa — Iniciar Sesión");
            stage.setScene(new Scene(root, 900, 600));
            stage.setMinWidth(900);
            stage.setMinHeight(600);
            if (estabaMaximizado) {
                stage.setMaximized(true);
            } else {
                stage.setWidth(900);
                stage.setHeight(600);
                stage.centerOnScreen();
            }
        } catch (Exception e) {
            controladorPrincipal.crearAlerta("Error: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}