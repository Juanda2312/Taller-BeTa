package co.edu.uniquindio.tallerbeta.service;

import co.edu.uniquindio.tallerbeta.repository.ClienteRepositorio;

public class ClienteServicio {
    private final ClienteRepositorio clienteRepositorio;

    public ClienteServicio(){
        this.clienteRepositorio = new ClienteRepositorio();
    }
}
