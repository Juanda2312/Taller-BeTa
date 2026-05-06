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
        for (Cliente c : clienteRepositorio.listarClientes()) {
            if (c.getCorreo().equalsIgnoreCase(correo) || c.getCedula().equals(cedula))
                throw new Exception("Ya existe un cliente con ese correo o cedula.");
        }
        clienteRepositorio.registrarCliente(new Cliente(nombre, cedula, correo, contrasenia));
    }

    public void iniciarSesion(String correo, String contrasenia) throws Exception {
        Cliente cliente = buscarPorCorreo(correo);
        cliente.iniciarSesion(correo, contrasenia);
    }

    public Cliente buscarPorCorreo(String correo) throws Exception {
        for (Cliente c : clienteRepositorio.listarClientes()) {
            if (c.getCorreo().equalsIgnoreCase(correo)) return c;
        }
        throw new Exception("No existe un usuario con ese correo.");
    }

    public LinkedList<Cliente> listarClientes() {
        return clienteRepositorio.listarClientes();
    }

    public boolean existeCliente(String cedula) {
        for (Cliente c : clienteRepositorio.listarClientes()) {
            if (c.getCedula().equals(cedula)) return true;
        }
        return false;
    }
}