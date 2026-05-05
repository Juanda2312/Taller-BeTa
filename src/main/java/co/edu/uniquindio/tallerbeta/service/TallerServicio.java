package co.edu.uniquindio.tallerbeta.service;

import co.edu.uniquindio.tallerbeta.model.Enum.Estado;
import co.edu.uniquindio.tallerbeta.model.entity.Cliente;
import co.edu.uniquindio.tallerbeta.model.entity.Mecanico;
import co.edu.uniquindio.tallerbeta.model.entity.Orden;
import co.edu.uniquindio.tallerbeta.repository.ClienteRepositorio;
import co.edu.uniquindio.tallerbeta.repository.MecanicoRepositorio;
import co.edu.uniquindio.tallerbeta.repository.OrdenRepositorio;
import co.edu.uniquindio.tallerbeta.service.Interface.ITallerServicio;

public class TallerServicio implements ITallerServicio {

    private final ClienteRepositorio  clienteRepositorio;
    private final MecanicoRepositorio mecanicoRepositorio;
    private final OrdenRepositorio    ordenRepositorio;

    public TallerServicio() {
        this.clienteRepositorio  = new ClienteRepositorio();
        this.mecanicoRepositorio = new MecanicoRepositorio();
        this.ordenRepositorio    = new OrdenRepositorio();
    }
    //talelr

    @Override
    public void registrarCliente(String nombre, String cedula,
                                 String correo, String contrasenia) throws Exception {
        boolean existe = clienteRepositorio.listarClientes().stream()
                .anyMatch(c -> c.getCorreo().equalsIgnoreCase(correo)
                        || c.getCedula().equals(cedula));
        if (existe) throw new Exception("Ya existe un cliente con ese correo o cédula.");

        clienteRepositorio.registrarCliente(new Cliente(nombre, cedula, correo, contrasenia));
    }

    @Override
    public void iniciarSesion(String correo, String contrasenia) throws Exception {
        Cliente cliente = clienteRepositorio.listarClientes().stream()
                .filter(c -> c.getCorreo().equalsIgnoreCase(correo))
                .findFirst()
                .orElseThrow(() -> new Exception("No existe un usuario con ese correo."));

        // iniciarSesion lanza RuntimeException si la contraseña no coincide
        cliente.iniciarSesion(correo, contrasenia);
    }

    //Cliente

    @Override
    public Orden realizarOrden(String instrucciones, Cliente cliente) {
        //máximo 10 órdenes activas al día
        long activas = ordenRepositorio.listarOrdenes().stream()
                .filter(o -> o.getEstado() != Estado.FINALIZADO)
                .count();
        if (activas >= 10)
            throw new RuntimeException("No se pueden registrar más de 10 órdenes activas en el día.");

        Orden orden = new Orden(instrucciones, cliente);
        ordenRepositorio.registrarOrden(orden);
        cliente.getListaOrdenes().add(orden);
        return orden;
    }

    //recepcionista

    @Override
    public void atenderCliente(Cliente cliente) {
        boolean existe = clienteRepositorio.listarClientes().stream()
                .anyMatch(c -> c.getCedula().equals(cliente.getCedula()));
        if (!existe) clienteRepositorio.registrarCliente(cliente);
    }

    // Mecánico

    @Override
    public double hacerPresupuesto() { return 0; }

    @Override
    public Orden realizarMantenimiento() { return null; }

    // Administrador

    @Override
    public Mecanico AsignarMecanico() {
        // solo mecánicos sin orden asignada
        return mecanicoRepositorio.listarMecanicos().stream()
                .filter(m -> m.getOrdenAsignada() == null)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No hay mecánicos disponibles."));
    }

    // Ayuda para los controladores

    public Cliente buscarClientePorCorreo(String correo) throws Exception {
        return clienteRepositorio.listarClientes().stream()
                .filter(c -> c.getCorreo().equalsIgnoreCase(correo))
                .findFirst()
                .orElseThrow(() -> new Exception("Cliente no encontrado."));
    }

    public void guardarOrdenes() {
        ordenRepositorio.guardarDatos(ordenRepositorio.listarOrdenes());
    }
}