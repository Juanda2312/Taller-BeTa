package co.edu.uniquindio.tallerbeta.service;

import co.edu.uniquindio.tallerbeta.model.entity.Cliente;
import co.edu.uniquindio.tallerbeta.repository.ClienteRepositorio;

import java.util.LinkedList;

/**
 * Servicio que proporciona la lógica de negocio para la gestión de clientes.
 * Interactúa con el repositorio de clientes para realizar operaciones CRUD y validaciones.
 */
public class ClienteServicio {

    private final ClienteRepositorio clienteRepositorio;

    /**
     * Constructor que inicializa el servicio con un repositorio de clientes.
     */
    public ClienteServicio() {
        this.clienteRepositorio = new ClienteRepositorio();
    }

    /**
     * Registra un nuevo cliente en el sistema.
     * Verifica que no exista otro cliente con el mismo correo o cédula.
     *
     * @param nombre El nombre del cliente.
     * @param cedula La cédula del cliente.
     * @param correo El correo electrónico del cliente.
     * @param contrasenia La contraseña del cliente.
     * @throws Exception Si ya existe un cliente con el mismo correo o cédula.
     */
    public void registrarCliente(String nombre, String cedula,
                                 String correo, String contrasenia) throws Exception {
        for (Cliente c : clienteRepositorio.listarClientes()) {
            if (c.getCorreo().equalsIgnoreCase(correo) || c.getCedula().equals(cedula))
                throw new Exception("Ya existe un cliente con ese correo o cedula.");
        }
        clienteRepositorio.registrarCliente(new Cliente(nombre, cedula, correo, contrasenia));
    }

    /**
     * Inicia sesión para un cliente utilizando correo y contraseña.
     *
     * @param correo El correo del cliente.
     * @param contrasenia La contraseña del cliente.
     * @throws Exception Si no se encuentra el cliente o las credenciales son incorrectas.
     */
    public void iniciarSesion(String correo, String contrasenia) throws Exception {
        Cliente cliente = buscarPorCorreo(correo);
        cliente.iniciarSesion(correo, contrasenia);
    }

    /**
     * Busca un cliente por su correo electrónico.
     *
     * @param correo El correo del cliente a buscar.
     * @return El cliente encontrado.
     * @throws Exception Si no existe un cliente con ese correo.
     */
    public Cliente buscarPorCorreo(String correo) throws Exception {
        for (Cliente c : clienteRepositorio.listarClientes()) {
            if (c.getCorreo().equalsIgnoreCase(correo)) return c;
        }
        throw new Exception("No existe un usuario con ese correo.");
    }

    /**
     * Retorna la lista completa de clientes.
     *
     * @return La lista de clientes.
     */
    public LinkedList<Cliente> listarClientes() {
        return clienteRepositorio.listarClientes();
    }

    /**
     * Verifica si existe un cliente con la cédula especificada.
     *
     * @param cedula La cédula a verificar.
     * @return true si existe, false en caso contrario.
     */
    public boolean existeCliente(String cedula) {
        for (Cliente c : clienteRepositorio.listarClientes()) {
            if (c.getCedula().equals(cedula)) return true;
        }
        return false;
    }
}