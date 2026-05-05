package co.edu.uniquindio.tallerbeta.service;

import co.edu.uniquindio.tallerbeta.model.entity.Cliente;
import co.edu.uniquindio.tallerbeta.repository.ClienteRepositorio;

import java.util.LinkedList;

public class ClienteServicio {

    private final ClienteRepositorio clienteRepositorio;

    public ClienteServicio() {
        this.clienteRepositorio = new ClienteRepositorio();
    }

    public void registrarCliente(String nombre, String cedula,
                                 String correo, String contrasenia) throws Exception {
        boolean existe = clienteRepositorio.listarClientes().stream()
                .anyMatch(c -> c.getCorreo().equalsIgnoreCase(correo)
                        || c.getCedula().equals(cedula));
        if (existe)
            throw new Exception("Ya existe un cliente con ese correo o cédula.");

        clienteRepositorio.registrarCliente(new Cliente(nombre, cedula, correo, contrasenia));
    }

    public void iniciarSesion(String correo, String contrasenia) throws Exception {
        Cliente cliente = buscarPorCorreo(correo);
        cliente.iniciarSesion(correo, contrasenia);
    }

    public Cliente buscarPorCorreo(String correo) throws Exception {
        return clienteRepositorio.listarClientes().stream()
                .filter(c -> c.getCorreo().equalsIgnoreCase(correo))
                .findFirst()
                .orElseThrow(() -> new Exception("No existe un usuario con ese correo."));
    }

    public LinkedList<Cliente> listarClientes() {
        return clienteRepositorio.listarClientes();
    }

    public boolean existeCliente(String cedula) {
        return clienteRepositorio.listarClientes().stream()
                .anyMatch(c -> c.getCedula().equals(cedula));
    }
}