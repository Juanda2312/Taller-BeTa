package co.edu.uniquindio.tallerbeta.controller;

import co.edu.uniquindio.tallerbeta.model.Enum.Estado;
import co.edu.uniquindio.tallerbeta.model.entity.Cliente;
import co.edu.uniquindio.tallerbeta.model.entity.Orden;
import co.edu.uniquindio.tallerbeta.service.TallerServicio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ordenController {

    private final ControladorPrincipal controladorPrincipal;
    private final TallerServicio       tallerServicio;
    private Cliente clienteActual;

    @FXML private Text      lblBienvenida;
    @FXML private TextArea  txtInstrucciones;
    @FXML private TextField txtId;
    @FXML private TextField txtEstado;
    @FXML private TextField txtBuscar;

    @FXML private TableView<Orden>           tablaOrdenes;
    @FXML private TableColumn<Orden, String> colId;
    @FXML private TableColumn<Orden, String> colInstrucciones;
    @FXML private TableColumn<Orden, String> colEstado;
    @FXML private TableColumn<Orden, String> colMecanico;

    @FXML private Text lblTotal;
    @FXML private Text lblPendientes;
    @FXML private Text lblEnProceso;
    @FXML private Text lblFinalizadas;

    private ObservableList<Orden> listaOrdenes;

    public ordenController() {
        controladorPrincipal = ControladorPrincipal.getInstance();
        tallerServicio       = controladorPrincipal.getTallerServicio();
    }

    @FXML
    public void initialize() {
        clienteActual = (Cliente) controladorPrincipal.getUsuario();
        lblBienvenida.setText("Bienvenido, " + clienteActual.getNombre());
        configurarTabla();
        cargarOrdenes();
        configurarSeleccion();
        configurarBusqueda();
    }

    private void configurarTabla() {
        colId.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getId().toString().substring(0, 8) + "..."));

        colInstrucciones.setCellValueFactory(
                new PropertyValueFactory<>("instrucciones"));

        colEstado.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getEstado().name()));

        colMecanico.setCellValueFactory(data -> {
            Orden o = data.getValue();
            String nombre = o.getMecanico() != null
                    ? o.getMecanico().getNombre() : "Sin asignar";
            return new SimpleStringProperty(nombre);
        });

        colEstado.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) { setText(null); setStyle(""); return; }
                setText(item);
                switch (item) {
                    case "SINASIGNAR" -> setStyle("-fx-text-fill: #ffd54f; -fx-font-weight: bold;");
                    case "ENPROCESO"  -> setStyle("-fx-text-fill: #4fc3f7; -fx-font-weight: bold;");
                    case "FINALIZADO" -> setStyle("-fx-text-fill: #81c784; -fx-font-weight: bold;");
                    default           -> setStyle("");
                }
            }
        });
    }

    @FXML
    public void cargarOrdenes() {
        listaOrdenes = FXCollections.observableArrayList(
                tallerServicio.listarOrdenesPorCliente(clienteActual));
        tablaOrdenes.setItems(listaOrdenes);
        actualizarContadores();
        String filtro = txtBuscar != null ? txtBuscar.getText() : "";
        if (!filtro.isBlank()) aplicarFiltro(filtro.trim().toLowerCase());
    }

    private void configurarSeleccion() {
        tablaOrdenes.getSelectionModel().selectedItemProperty().addListener(
                (obs, anterior, seleccionada) -> {
                    if (seleccionada != null) {
                        txtId.setText(seleccionada.getId().toString());
                        txtInstrucciones.setText(seleccionada.getInstrucciones());
                        txtEstado.setText(seleccionada.getEstado().name());
                    }
                });
    }

    private void configurarBusqueda() {
        txtBuscar.textProperty().addListener((obs, anterior, nuevo) -> {
            if (nuevo == null || nuevo.isBlank()) {
                tablaOrdenes.setItems(listaOrdenes);
            } else {
                aplicarFiltro(nuevo.trim().toLowerCase());
            }
        });
    }

    private void aplicarFiltro(String texto) {
        ObservableList<Orden> filtrada = FXCollections.observableArrayList();
        for (Orden o : listaOrdenes) {
            boolean coincideEstado        = o.getEstado().name().toLowerCase().contains(texto);
            boolean coincideInstrucciones = o.getInstrucciones().toLowerCase().contains(texto);
            String mecanico = o.getMecanico() != null
                    ? o.getMecanico().getNombre().toLowerCase() : "";
            boolean coincideMecanico = mecanico.contains(texto);
            if (coincideEstado || coincideInstrucciones || coincideMecanico) {
                filtrada.add(o);
            }
        }
        tablaOrdenes.setItems(filtrada);
    }

    @FXML
    void crearOrden(ActionEvent event) {
        String instrucciones = txtInstrucciones.getText().trim();
        if (instrucciones.isEmpty()) {
            controladorPrincipal.crearAlerta(
                    "Escribe la descripcion del problema.", Alert.AlertType.WARNING);
            return;
        }
        try {
            tallerServicio.realizarOrden(instrucciones, clienteActual);
            controladorPrincipal.crearAlerta(
                    "Orden creada exitosamente!", Alert.AlertType.INFORMATION);
            limpiarFormulario();
            cargarOrdenes();
        } catch (Exception e) {
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void actualizarOrden(ActionEvent event) {
        Orden seleccionada = tablaOrdenes.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            controladorPrincipal.crearAlerta(
                    "Selecciona una orden de la tabla para editar.", Alert.AlertType.WARNING);
            return;
        }
        if (seleccionada.getEstado() != Estado.SINASIGNAR) {
            controladorPrincipal.crearAlerta(
                    "Solo puedes editar ordenes sin asignar. (RN)", Alert.AlertType.WARNING);
            return;
        }
        String nuevasInstrucciones = txtInstrucciones.getText().trim();
        if (nuevasInstrucciones.isEmpty()) {
            controladorPrincipal.crearAlerta(
                    "Escribe las nuevas instrucciones.", Alert.AlertType.WARNING);
            return;
        }
        try {
            tallerServicio.actualizarInstruccionesOrden(
                    seleccionada.getId(), nuevasInstrucciones);
            controladorPrincipal.crearAlerta(
                    "Orden actualizada correctamente.", Alert.AlertType.INFORMATION);
            limpiarFormulario();
            cargarOrdenes();
        } catch (Exception e) {
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void eliminarOrden(ActionEvent event) {
        Orden seleccionada = tablaOrdenes.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            controladorPrincipal.crearAlerta(
                    "Selecciona una orden para eliminar.", Alert.AlertType.WARNING);
            return;
        }
        if (seleccionada.getEstado() != Estado.SINASIGNAR) {
            controladorPrincipal.crearAlerta(
                    "Solo puedes eliminar ordenes sin asignar. (RN)", Alert.AlertType.WARNING);
            return;
        }
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminacion");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("Estas seguro de eliminar esta orden?");
        confirmacion.showAndWait().ifPresent(respuesta -> {
            if (respuesta == ButtonType.OK) {
                try {
                    tallerServicio.eliminarOrden(seleccionada.getId());
                    controladorPrincipal.crearAlerta(
                            "Orden eliminada.", Alert.AlertType.INFORMATION);
                    limpiarFormulario();
                    cargarOrdenes();
                } catch (Exception e) {
                    controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
                }
            }
        });
    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        try {
            controladorPrincipal.setUsuario(null);
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co/edu/uniquindio/tallerbeta/login.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Iniciar Sesion");
            stage.setScene(new Scene(root, 600, 400));
            stage.setResizable(false);
            stage.show();
            controladorPrincipal.cerrarVentana(lblBienvenida);
        } catch (Exception e) {
            controladorPrincipal.crearAlerta(
                    "Error al cerrar sesion: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void actualizarContadores() {
        long total = listaOrdenes.size();
        long sinAsignar = 0, enProceso = 0, finalizadas = 0;
        for (Orden o : listaOrdenes) {
            if      (o.getEstado() == Estado.SINASIGNAR) sinAsignar++;
            else if (o.getEstado() == Estado.ENPROCESO)  enProceso++;
            else if (o.getEstado() == Estado.FINALIZADO) finalizadas++;
        }
        lblTotal.setText(String.valueOf(total));
        lblPendientes.setText(String.valueOf(sinAsignar));
        lblEnProceso.setText(String.valueOf(enProceso));
        lblFinalizadas.setText(String.valueOf(finalizadas));
    }

    private void limpiarFormulario() {
        txtInstrucciones.clear();
        txtId.clear();
        txtEstado.clear();
        tablaOrdenes.getSelectionModel().clearSelection();
    }
}