package co.edu.uniquindio.tallerbeta.service;

import co.edu.uniquindio.tallerbeta.model.entity.Administrador;
import co.edu.uniquindio.tallerbeta.model.entity.Mecanico;
import co.edu.uniquindio.tallerbeta.model.entity.Orden;
import co.edu.uniquindio.tallerbeta.repository.AdministradorRepositorio;
import co.edu.uniquindio.tallerbeta.repository.MecanicoRepositorio;
import co.edu.uniquindio.tallerbeta.repository.OrdenRepositorio;

import java.util.LinkedList;

public class AdministradorServicio {

    private final AdministradorRepositorio administradorRepositorio;
    private final MecanicoRepositorio      mecanicoRepositorio;
    private final OrdenRepositorio         ordenRepositorio;

    public AdministradorServicio() {
        this.administradorRepositorio = new AdministradorRepositorio();
        this.mecanicoRepositorio      = new MecanicoRepositorio();
        this.ordenRepositorio         = new OrdenRepositorio();
    }

    public Mecanico asignarMecanico() {
        // RN-03: solo mecanicos sin orden asignada
        for (Mecanico m : mecanicoRepositorio.listarMecanicos()) {
            if (m.getOrdenAsignada() == null) return m;
        }
        throw new RuntimeException("No hay mecanicos disponibles.");
    }

    public void asignarMecanicoAOrden(Orden orden) {
        Mecanico mecanico = asignarMecanico();
        orden.setMecanico(mecanico);
        mecanico.setOrdenAsignada(orden);
        mecanicoRepositorio.guardarDatos(mecanicoRepositorio.listarMecanicos());
        ordenRepositorio.guardarDatos(ordenRepositorio.listarOrdenes());
    }

    public LinkedList<Administrador> listarAdministradores() {
        return administradorRepositorio.listarAdministradores();
    }
}