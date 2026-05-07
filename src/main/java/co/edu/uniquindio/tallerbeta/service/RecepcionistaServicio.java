package co.edu.uniquindio.tallerbeta.service;

import co.edu.uniquindio.tallerbeta.model.entity.Cliente;
import co.edu.uniquindio.tallerbeta.model.entity.Recepcionista;
import co.edu.uniquindio.tallerbeta.repository.ClienteRepositorio;
import co.edu.uniquindio.tallerbeta.repository.RecepcionistaRepositorio;

import java.util.LinkedList;

/**
 * Servicio que proporciona la lógica de negocio para la gestión de recepcionistas.
 * Permite atender clientes y listar recepcionistas.
 */
public class RecepcionistaServicio {

    private final RecepcionistaRepositorio recepcionistaRepositorio;
    private final ClienteRepositorio clienteRepositorio;

    /**
     * Constructor que inicializa el servicio con repositorios de recepcionistas y clientes.
     */
    public RecepcionistaServicio() {
        this.recepcionistaRepositorio = new RecepcionistaRepositorio();
        this.clienteRepositorio = new ClienteRepositorio();
    }

    /**
     * Permite a un recepcionista atender a un cliente.
     * Si el cliente no existe en el sistema, se registra automáticamente.
     * Registra al cliente en la lista del primer recepcionista disponible.
     *
     * @param cliente El cliente a atender.
     */
    public void atenderCliente(Cliente cliente) {
        // RN: si el cliente no existe en el sistema, se registra
        boolean existe = clienteRepositorio.listarClientes().stream()
                .anyMatch(c -> c.getCedula().equals(cliente.getCedula()));
        if (!existe)
            clienteRepositorio.registrarCliente(cliente);

        // se registra en la lista del recepcionista activo si hay uno
        recepcionistaRepositorio.listarRecepcionistas().stream()
                .findFirst()
                .ifPresent(r -> r.AtenderCliente(cliente));
    }

    /**
     * Retorna la lista completa de recepcionistas.
     *
     * @return La lista de recepcionistas.
     */
    public LinkedList<Recepcionista> listarRecepcionistas() {
        return recepcionistaRepositorio.listarRecepcionistas();
    }
}