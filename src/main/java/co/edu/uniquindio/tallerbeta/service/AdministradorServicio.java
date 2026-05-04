package co.edu.uniquindio.tallerbeta.service;

import co.edu.uniquindio.tallerbeta.repository.AdministradorRepositorio;

public class AdministradorServicio {
    private final AdministradorRepositorio administradorRepositorio;

    public AdministradorServicio(){
        this.administradorRepositorio = new AdministradorRepositorio();
    }
}
