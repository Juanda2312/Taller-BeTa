package co.edu.uniquindio.tallerbeta.service;

import co.edu.uniquindio.tallerbeta.model.Enum.Especializacion;
import co.edu.uniquindio.tallerbeta.model.entity.Mecanico;
import co.edu.uniquindio.tallerbeta.model.entity.Orden;
import co.edu.uniquindio.tallerbeta.repository.MecanicoRepositorio;

import java.util.LinkedList;

public class MecanicoServicio {

    private final MecanicoRepositorio mecanicoRepositorio;

    public MecanicoServicio() {
        this.mecanicoRepositorio = new MecanicoRepositorio();
    }

    public Mecanico buscarMecanicoDisponible() {
        for (Mecanico m : mecanicoRepositorio.listarMecanicos()) {
            if (m.getOrdenAsignada() == null) return m;
        }
        throw new RuntimeException("No hay mecanicos disponibles.");
    }

    public Mecanico buscarMecanicoDisponiblePorEspecializacion(Especializacion especializacion) {
        for (Mecanico m : mecanicoRepositorio.listarMecanicos()) {
            if (m.getOrdenAsignada() == null && m.getEspecializacion() == especializacion) {
                return m;
            }
        }
        throw new RuntimeException("No hay mecanicos disponibles con esa especializacion.");
    }

    public Orden realizarMantenimientoConOrden() {
        for (Mecanico m : mecanicoRepositorio.listarMecanicos()) {
            if (m.getOrdenAsignada() != null) {
                Orden finalizada = m.realizarMantenimiento();
                mecanicoRepositorio.guardarDatos(mecanicoRepositorio.listarMecanicos());
                return finalizada;
            }
        }
        throw new RuntimeException("No hay mecanicos con orden asignada.");
    }

    public LinkedList<Mecanico> listarMecanicos() {
        return mecanicoRepositorio.listarMecanicos();
    }

    public LinkedList<Mecanico> listarMecanicosDisponibles() {
        LinkedList<Mecanico> disponibles = new LinkedList<>();
        for (Mecanico m : mecanicoRepositorio.listarMecanicos()) {
            if (m.getOrdenAsignada() == null) disponibles.add(m);
        }
        return disponibles;
    }

    public void registrarMecanico(String nombre, String cedula,
                                  Especializacion especializacion) throws Exception {
        for (Mecanico m : mecanicoRepositorio.listarMecanicos()) {
            if (m.getCedula().equals(cedula))
                throw new Exception("Ya existe un mecanico con esa cedula.");
        }
        mecanicoRepositorio.registrarMecanico(new Mecanico(nombre, cedula, especializacion));
    }
}