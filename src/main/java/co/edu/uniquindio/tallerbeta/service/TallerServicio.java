package co.edu.uniquindio.tallerbeta.service;

import co.edu.uniquindio.tallerbeta.model.Enum.Estado;
import co.edu.uniquindio.tallerbeta.model.entity.Cliente;
import co.edu.uniquindio.tallerbeta.model.entity.Mecanico;
import co.edu.uniquindio.tallerbeta.model.entity.Orden;
import co.edu.uniquindio.tallerbeta.service.Interface.ITallerServicio;

import java.util.LinkedList;
import java.util.UUID;

/**
 * Servicio principal del taller que implementa la interfaz ITallerServicio.
 * Coordina las operaciones entre los diferentes servicios del sistema.
 */
public class TallerServicio implements ITallerServicio {

    private final ClienteServicio       clienteServicio;
    private final OrdenServicio         ordenServicio;
    private final MecanicoServicio      mecanicoServicio;
    private final RecepcionistaServicio recepcionistaServicio;
    private final AdministradorServicio administradorServicio;

    /**
     * Constructor que inicializa todos los servicios necesarios.
     */
    public TallerServicio() {
        this.clienteServicio       = new ClienteServicio();
        this.ordenServicio         = new OrdenServicio();
        this.mecanicoServicio      = new MecanicoServicio();
        this.recepcionistaServicio = new RecepcionistaServicio();
        this.administradorServicio = new AdministradorServicio();
    }

    // Taller
    /**
     * Registra un nuevo cliente en el sistema.
     *
     * @param nombre El nombre del cliente.
     * @param cedula La cédula del cliente.
     * @param correo El correo electrónico del cliente.
     * @param contrasenia La contraseña del cliente.
     * @throws Exception Si ocurre un error en el registro.
     */
    @Override
    public void registrarCliente(String nombre, String cedula,
                                 String correo, String contrasenia) throws Exception {
        clienteServicio.registrarCliente(nombre, cedula, correo, contrasenia);
    }

    /**
     * Inicia sesión para un cliente.
     *
     * @param correo El correo del cliente.
     * @param contrasenia La contraseña del cliente.
     * @throws Exception Si las credenciales son incorrectas.
     */
    @Override
    public void iniciarSesion(String correo, String contrasenia) throws Exception {
        clienteServicio.iniciarSesion(correo, contrasenia);
    }

    // Cliente
    /**
     * Permite a un cliente realizar una orden de mantenimiento.
     *
     * @param instrucciones Las instrucciones de la orden.
     * @param cliente El cliente que realiza la orden.
     * @return La orden creada.
     */
    @Override
    public Orden realizarOrden(String instrucciones, Cliente cliente) {
        return ordenServicio.crearOrden(instrucciones, cliente);
    }

    // Recepcionista
    /**
     * Permite atender a un cliente.
     *
     * @param cliente El cliente a atender.
     */
    @Override
    public void atenderCliente(Cliente cliente) {
        recepcionistaServicio.atenderCliente(cliente);
    }

    // Mecanico
    /**
     * Calcula el presupuesto para una orden asignada a un mecánico disponible.
     *
     * @return El presupuesto calculado.
     */
    @Override
    public double hacerPresupuesto() {
        return mecanicoServicio.buscarMecanicoDisponible().hacerPresupuesto();
    }

    /**
     * Realiza el mantenimiento de una orden asignada.
     *
     * @return La orden finalizada.
     */
    @Override
    public Orden realizarMantenimiento() {
        return mecanicoServicio.realizarMantenimientoConOrden();
    }

    // Administrador
    /**
     * Asigna un mecánico disponible.
     *
     * @return El mecánico asignado.
     */
    @Override
    public Mecanico AsignarMecanico() {
        return administradorServicio.asignarMecanico();
    }

    // Metodos de apoyo para los controladores
    /**
     * Busca un cliente por su correo electrónico.
     *
     * @param correo El correo del cliente.
     * @return El cliente encontrado.
     * @throws Exception Si no se encuentra el cliente.
     */
    public Cliente buscarClientePorCorreo(String correo) throws Exception {
        return clienteServicio.buscarPorCorreo(correo);
    }

    /**
     * Lista las órdenes de un cliente específico.
     *
     * @param cliente El cliente cuyas órdenes se buscan.
     * @return La lista de órdenes del cliente.
     */
    public LinkedList<Orden> listarOrdenesPorCliente(Cliente cliente) {
        return ordenServicio.listarOrdenesPorCliente(cliente);
    }

    /**
     * Lista las órdenes con un estado específico.
     *
     * @param estado El estado de las órdenes.
     * @return La lista de órdenes con ese estado.
     */
    public LinkedList<Orden> listarOrdenesPorEstado(Estado estado) {
        return ordenServicio.listarOrdenesPorEstado(estado);
    }

    /**
     * Lista todas las órdenes del sistema.
     *
     * @return La lista completa de órdenes.
     */
    public LinkedList<Orden> listarTodasLasOrdenes() {
        return ordenServicio.listarOrdenes();
    }

    /**
     * Elimina una orden por su ID.
     *
     * @param id El ID de la orden a eliminar.
     */
    public void eliminarOrden(UUID id) {
        ordenServicio.eliminarOrden(id);
    }

    /**
     * Actualiza las instrucciones de una orden.
     *
     * @param id El ID de la orden.
     * @param nuevasInstrucciones Las nuevas instrucciones.
     */
    public void actualizarInstruccionesOrden(UUID id, String nuevasInstrucciones) {
        ordenServicio.actualizarInstrucciones(id, nuevasInstrucciones);
    }
}