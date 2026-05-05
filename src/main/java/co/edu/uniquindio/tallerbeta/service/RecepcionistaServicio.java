package co.edu.uniquindio.tallerbeta.service;

import co.edu.uniquindio.tallerbeta.model.entity.Cliente;
import co.edu.uniquindio.tallerbeta.model.entity.Recepcionista;
import co.edu.uniquindio.tallerbeta.repository.ClienteRepositorio;
import co.edu.uniquindio.tallerbeta.repository.RecepcionistaRepositorio;

import java.util.LinkedList;

public class RecepcionistaServicio {

    private final RecepcionistaRepositorio recepcionistaRepositorio;
    private final ClienteRepositorio clienteRepositorio;

    public RecepcionistaServicio() {
        this.recepcionistaRepositorio = new RecepcionistaRepositorio();
        this.clienteRepositorio = new ClienteRepositorio();
    }

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

    public LinkedList<Recepcionista> listarRecepcionistas() {
        return recepcionistaRepositorio.listarRecepcionistas();
    }
}