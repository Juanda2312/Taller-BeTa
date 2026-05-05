package co.edu.uniquindio.tallerbeta.controller;

import co.edu.uniquindio.tallerbeta.model.entity.Usuario;
import co.edu.uniquindio.tallerbeta.service.TallerServicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class registrocontroller {

    private final ControladorPrincipal controladorPrincipal;
    private final TallerServicio tallerServicio;

    @FXML
    private Button btnregistrarse;

    @FXML
    private Button btnvolver;

    @FXML
    private TextField txtcedula;

    @FXML
    private PasswordField txtconfirmarcontraseña;

    @FXML
    private PasswordField txtcontraseña;

    @FXML
    private TextField txtcorreo;

    @FXML
    private TextField txtnombre;

    public registrocontroller() {
        controladorPrincipal = ControladorPrincipal.getInstance();
        tallerServicio = controladorPrincipal.getTallerServicio();
    }

    @FXML
    void registrarse(ActionEvent event) {

    }

    @FXML
    void volver(ActionEvent event) {

    }

}
