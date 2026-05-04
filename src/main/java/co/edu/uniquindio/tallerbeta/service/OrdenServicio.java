package co.edu.uniquindio.tallerbeta.service;

import co.edu.uniquindio.tallerbeta.repository.OrdenRepositorio;

public class OrdenServicio {
    private final OrdenRepositorio ordenRepositorio;

    public OrdenServicio(){
        this.ordenRepositorio = new OrdenRepositorio();
    }
}
