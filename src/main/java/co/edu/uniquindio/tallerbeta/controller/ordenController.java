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
    @FXML private Button    btnCerrarSesion;

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
        colId.setCellFactory(col -> new TableCell<>() {
            @Override protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) { setText(null); setStyle(""); return; }
                setText(item);
                setStyle("-fx-text-fill: #0a0808; -fx-font-family: Consolas; -fx-font-size: 11;");
            }
        });

        colInstrucciones.setCellValueFactory(new PropertyValueFactory<>("instrucciones"));
        colInstrucciones.setCellFactory(col -> new TableCell<>() {
            @Override protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) { setText(null); setStyle(""); return; }
                setText(item);
                setStyle("-fx-text-fill: #0a0808; -fx-font-size: 12; -fx-font-family: Consolas;");
            }
        });

        colEstado.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getEstado().name()));
        colEstado.setCellFactory(col -> new TableCell<>() {
            @Override protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) { setText(null); setGraphic(null); setStyle(""); return; }
                Label badge = new Label(item);
                String base = "-fx-background-radius: 20; -fx-padding: 4 12 4 12;"
                        + " -fx-font-size: 10; -fx-font-weight: bold; -fx-font-family: Consolas;";
                switch (item) {
                    case "SINASIGNAR" -> badge.setStyle(base
                            + "-fx-background-color: #3a2d00; -fx-text-fill: #f6c90e;");
                    case "ENPROCESO"  -> badge.setStyle(base
                            + "-fx-background-color: #0d2d18; -fx-text-fill: #3fb950;");
                    case "FINALIZADO" -> badge.setStyle(base
                            + "-fx-background-color: #1e1040; -fx-text-fill: #a371f7;");
                    default -> badge.setStyle(base
                            + "-fx-background-color: #21262d; -fx-text-fill: #8b949e;");
                }
                setText(null);
                setGraphic(badge);
                setStyle("-fx-alignment: CENTER;");
            }
        });

        colMecanico.setCellValueFactory(data -> {
            Orden o = data.getValue();
            String nombre = o.getMecanico() != null ? o.getMecanico().getNombre() : "—";
            return new SimpleStringProperty(nombre);
        });
        colMecanico.setCellFactory(col -> new TableCell<>() {
            @Override protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) { setText(null); setStyle(""); return; }
                setText(item);
                setStyle("—".equals(item)
                        ? "-fx-text-fill: #0a0808; -fx-font-family: Consolas; -fx-font-size: 12;"
                        : "-fx-text-fill: #0a0808; -fx-font-family: Consolas; -fx-font-size: 12; -fx-font-weight: bold;");
            }
        });
    }

    @FXML
    public void cargarOrdenes() {
        listaOrdenes = FXCollections.observableArrayList(
                tallerServicio.listarOrdenesPorCliente(clienteActual));
        actualizarContadores();
        if (txtBuscar != null && !txtBuscar.getText().isBlank()) {
            aplicarFiltro(txtBuscar.getText().trim().toLowerCase());
        } else {
            tablaOrdenes.setItems(listaOrdenes);
        }
        tablaOrdenes.refresh();
    }

    private void configurarSeleccion() {
        tablaOrdenes.getSelectionModel().selectedItemProperty().addListener(
                (obs, anterior, sel) -> {
                    if (sel != null) {
                        txtId.setText(sel.getId().toString());
                        txtInstrucciones.clear();
                        txtInstrucciones.setText(sel.getInstrucciones());
                        txtEstado.setText(sel.getEstado().name());
                    }
                });
    }

    private void configurarBusqueda() {
        txtBuscar.textProperty().addListener((obs, ant, nuevo) -> {
            if (nuevo == null || nuevo.isBlank()) tablaOrdenes.setItems(listaOrdenes);
            else aplicarFiltro(nuevo.trim().toLowerCase());
        });
    }

    private void aplicarFiltro(String texto) {
        ObservableList<Orden> filtrada = FXCollections.observableArrayList();
        for (Orden o : listaOrdenes) {
            boolean est  = o.getEstado().name().toLowerCase().contains(texto);
            boolean inst = o.getInstrucciones().toLowerCase().contains(texto);
            String mec   = o.getMecanico() != null
                    ? o.getMecanico().getNombre().toLowerCase() : "";
            if (est || inst || mec.contains(texto)) filtrada.add(o);
        }
        tablaOrdenes.setItems(filtrada);
    }

    @FXML
    void crearOrden(ActionEvent event) {
        String inst = txtInstrucciones.getText().trim();
        if (inst.isEmpty()) {
            controladorPrincipal.crearAlerta(
                    "Escribe la descripción del problema.", Alert.AlertType.WARNING);
            return;
        }
        try {
            tallerServicio.realizarOrden(inst, clienteActual);
            controladorPrincipal.crearAlerta("Orden creada exitosamente.", Alert.AlertType.INFORMATION);
            limpiarFormulario();
            cargarOrdenes();
        } catch (Exception e) {
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void actualizarOrden(ActionEvent event) {
        Orden sel = tablaOrdenes.getSelectionModel().getSelectedItem();
        if (sel == null) {
            controladorPrincipal.crearAlerta("Selecciona una orden para editar.", Alert.AlertType.WARNING);
            return;
        }
        if (sel.getEstado() != Estado.SINASIGNAR) {
            controladorPrincipal.crearAlerta("Solo puedes editar órdenes SINASIGNAR.", Alert.AlertType.WARNING);
            return;
        }
        String nuevas = txtInstrucciones.getText().trim();
        if (nuevas.isEmpty()) {
            controladorPrincipal.crearAlerta("Escribe las nuevas instrucciones.", Alert.AlertType.WARNING);
            return;
        }
        try {
            tallerServicio.actualizarInstruccionesOrden(sel.getId(), nuevas);
            controladorPrincipal.crearAlerta("Orden actualizada.", Alert.AlertType.INFORMATION);
            limpiarFormulario();
            cargarOrdenes();
        } catch (Exception e) {
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void eliminarOrden(ActionEvent event) {
        Orden sel = tablaOrdenes.getSelectionModel().getSelectedItem();
        if (sel == null) {
            controladorPrincipal.crearAlerta("Selecciona una orden para eliminar.", Alert.AlertType.WARNING);
            return;
        }
        if (sel.getEstado() != Estado.SINASIGNAR) {
            controladorPrincipal.crearAlerta("Solo puedes eliminar órdenes SINASIGNAR.", Alert.AlertType.WARNING);
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar eliminación");
        confirm.setHeaderText(null);
        confirm.setContentText("¿Estás seguro de eliminar esta orden?");
        confirm.showAndWait().ifPresent(r -> {
            if (r == ButtonType.OK) {
                try {
                    tallerServicio.eliminarOrden(sel.getId());
                    controladorPrincipal.crearAlerta("Orden eliminada.", Alert.AlertType.INFORMATION);
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

            // FIX: reusar Stage actual — conserva botón maximizar
            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            boolean estabaMaximizado = stage.isMaximized();

            stage.setTitle("Taller BeTa — Iniciar Sesión");
            stage.setScene(new Scene(root, 900, 600));
            stage.setMinWidth(900);
            stage.setMinHeight(600);
            stage.setResizable(true);

            if (estabaMaximizado) {
                stage.setMaximized(true);
            } else {
                stage.setWidth(900);
                stage.setHeight(600);
                stage.centerOnScreen();
            }
        } catch (Exception e) {
            controladorPrincipal.crearAlerta(
                    "Error al cerrar sesión: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void actualizarContadores() {
        long total = listaOrdenes.size(), sin = 0, en = 0, fin = 0;
        for (Orden o : listaOrdenes) {
            if      (o.getEstado() == Estado.SINASIGNAR) sin++;
            else if (o.getEstado() == Estado.ENPROCESO)  en++;
            else if (o.getEstado() == Estado.FINALIZADO) fin++;
        }
        lblTotal.setText(String.valueOf(total));
        lblPendientes.setText(String.valueOf(sin));
        lblEnProceso.setText(String.valueOf(en));
        lblFinalizadas.setText(String.valueOf(fin));
    }

    private void limpiarFormulario() {
        txtInstrucciones.clear();
        txtId.clear();
        txtEstado.clear();
        tablaOrdenes.getSelectionModel().clearSelection();
    }
}