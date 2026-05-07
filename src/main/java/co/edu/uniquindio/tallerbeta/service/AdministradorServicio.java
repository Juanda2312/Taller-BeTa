package co.edu.uniquindio.tallerbeta.service;

import co.edu.uniquindio.tallerbeta.model.entity.Administrador;
import co.edu.uniquindio.tallerbeta.model.entity.Mecanico;
import co.edu.uniquindio.tallerbeta.model.entity.Orden;
import co.edu.uniquindio.tallerbeta.repository.AdministradorRepositorio;
import co.edu.uniquindio.tallerbeta.repository.MecanicoRepositorio;
import co.edu.uniquindio.tallerbeta.repository.OrdenRepositorio;

import java.util.LinkedList;

/**
 * Servicio que proporciona la lógica de negocio para la gestión de administradores.
 * Permite asignar mecánicos a órdenes y listar administradores.
 */
public class AdministradorServicio {

    private final AdministradorRepositorio administradorRepositorio;
    private final MecanicoRepositorio      mecanicoRepositorio;
    private final OrdenRepositorio         ordenRepositorio;

    /**
     * Constructor que inicializa el servicio con repositorios de administradores, mecánicos y órdenes.
     */
    public AdministradorServicio() {
        this.administradorRepositorio = new AdministradorRepositorio();
        this.mecanicoRepositorio      = new MecanicoRepositorio();
        this.ordenRepositorio         = new OrdenRepositorio();
    }

    /**
     * Asigna un mecánico disponible (sin orden asignada) a una orden.
     * Regla de negocio RN-03: solo mecánicos sin orden asignada.
     *
     * @return El mecánico asignado.
     * @throws RuntimeException Si no hay mecánicos disponibles.
     */
    public Mecanico asignarMecanico() {
        // RN-03: solo mecanicos sin orden asignada
        for (Mecanico m : mecanicoRepositorio.listarMecanicos()) {
            if (m.getOrdenAsignada() == null) return m;
        }
        throw new RuntimeException("No hay mecanicos disponibles.");
    }

    /**
     * Asigna un mecánico disponible a una orden específica.
     * Actualiza la orden y el mecánico, y guarda los datos.
     *
     * @param orden La orden a la que asignar el mecánico.
     */
    public void asignarMecanicoAOrden(Orden orden) {
        Mecanico mecanico = asignarMecanico();
        orden.setMecanico(mecanico);
        mecanico.setOrdenAsignada(orden);
        mecanicoRepositorio.guardarDatos(mecanicoRepositorio.listarMecanicos());
        ordenRepositorio.guardarDatos(ordenRepositorio.listarOrdenes());
    }

    /**
     * Retorna la lista completa de administradores.
     *
     * @return La lista de administradores.
     */
    public LinkedList<Administrador> listarAdministradores() {
        return administradorRepositorio.listarAdministradores();
    }
}