package co.edu.uniquindio.tallerbeta.service;

import co.edu.uniquindio.tallerbeta.model.Enum.Estado;
import co.edu.uniquindio.tallerbeta.model.entity.Cliente;
import co.edu.uniquindio.tallerbeta.model.entity.Mecanico;
import co.edu.uniquindio.tallerbeta.model.entity.Orden;
import co.edu.uniquindio.tallerbeta.service.Interface.ITallerServicio;

import java.util.LinkedList;

public class TallerServicio implements ITallerServicio {

    private final ClienteServicio      clienteServicio;
    private final OrdenServicio        ordenServicio;
    private final MecanicoServicio     mecanicoServicio;
    private final RecepcionistaServicio recepcionistaServicio;
    private final AdministradorServicio administradorServicio;

    public TallerServicio() {
        this.clienteServicio       = new ClienteServicio();
        this.ordenServicio         = new OrdenServicio();
        this.mecanicoServicio      = new MecanicoServicio();
        this.recepcionistaServicio = new RecepcionistaServicio();
        this.administradorServicio = new AdministradorServicio();
    }

    // Taller

    @Override
    public void registrarCliente(String nombre, String cedula,
                                 String correo, String contrasenia) throws Exception {
        clienteServicio.registrarCliente(nombre, cedula, correo, contrasenia);
    }

    @Override
    public void iniciarSesion(String correo, String contrasenia) throws Exception {
        clienteServicio.iniciarSesion(correo, contrasenia);
    }

    //Cliente

    @Override
    public Orden realizarOrden(String instrucciones, Cliente cliente) {
        return ordenServicio.crearOrden(instrucciones, cliente);
    }

    //Recepcionista

    @Override
    public void atenderCliente(Cliente cliente) {
        recepcionistaServicio.atenderCliente(cliente);
    }

    //Mecánico

    @Override
    public double hacerPresupuesto() {
        // se delega al mecánico asignado a la orden correspondiente
        return mecanicoServicio.buscarMecanicoDisponible().hacerPresupuesto();
    }

    @Override
    public Orden realizarMantenimiento() {
        Mecanico mecanico = mecanicoServicio.listarMecanicos().stream()
                .filter(m -> m.getOrdenAsignada() != null)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No hay mecánicos con orden asignada."));
        return mecanico.realizarMantenimiento();
    }

    //Mecánico
    @Override
    public Mecanico AsignarMecanico() {
        return administradorServicio.asignarMecanico();
    }

    //Ayuda para los controladores

    public Cliente buscarClientePorCorreo(String correo) throws Exception {
        return clienteServicio.buscarPorCorreo(correo);
    }

    public LinkedList<Orden> listarOrdenesPorCliente(Cliente cliente) {
        return ordenServicio.listarOrdenesPorCliente(cliente);
    }

    public LinkedList<Orden> listarOrdenesPorEstado(Estado estado) {
        return ordenServicio.listarOrdenesPorEstado(estado);
    }

    public LinkedList<Orden> listarTodasLasOrdenes() {
        return ordenServicio.listarOrdenes();
    }
    public void eliminarOrden(java.util.UUID id) {
        ordenServicio.eliminarOrden(id);
    }

    public void guardarOrdenes() {
        ordenServicio.listarOrdenes(); // ya persiste en el repo
    }
}