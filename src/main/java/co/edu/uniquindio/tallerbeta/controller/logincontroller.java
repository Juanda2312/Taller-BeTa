package co.edu.uniquindio.tallerbeta.controller;

import co.edu.uniquindio.tallerbeta.model.entity.Usuario;
import co.edu.uniquindio.tallerbeta.service.TallerServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class logincontroller {

    private final ControladorPrincipal controladorPrincipal;
    private final Usuario usuario;
    private final TallerServicio tallerServicio;

    @FXML
    private Button btniniciarsesion;

    @FXML
    private Button btnregistrarse;

    @FXML
    private PasswordField txtcontraseña;

    @FXML
    private TextField txtcorreo;

    public logincontroller(){
        controladorPrincipal = ControladorPrincipal.getInstance();
        usuario = controladorPrincipal.getUsuario();
        tallerServicio = controladorPrincipal.getTallerServicio();
    }

    @FXML
    void Registrarse(ActionEvent event) {

    }

    @FXML
    void iniciarSesion(ActionEvent event) {

    }

}