package co.edu.uniquindio.tallerbeta.service;

import co.edu.uniquindio.tallerbeta.model.Enum.Especializacion;
import co.edu.uniquindio.tallerbeta.model.entity.Mecanico;
import co.edu.uniquindio.tallerbeta.repository.MecanicoRepositorio;

import java.util.LinkedList;

public class MecanicoServicio {

    private final MecanicoRepositorio mecanicoRepositorio;

    public MecanicoServicio() {
        this.mecanicoRepositorio = new MecanicoRepositorio();
    }

    public Mecanico buscarMecanicoDisponible() {
        return mecanicoRepositorio.listarMecanicos().stream()
                .filter(m -> m.getOrdenAsignada() == null)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No hay mecánicos disponibles."));
    }

    public Mecanico buscarMecanicoDisponiblePorEspecializacion(Especializacion especializacion) {
        return mecanicoRepositorio.listarMecanicos().stream()
                .filter(m -> m.getOrdenAsignada() == null
                        && m.getEspecializacion() == especializacion)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "No hay mecánicos disponibles con esa especialización."));
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
        boolean existe = mecanicoRepositorio.listarMecanicos().stream()
                .anyMatch(m -> m.getCedula().equals(cedula));
        if (existe)
            throw new Exception("Ya existe un mecánico con esa cédula.");
        mecanicoRepositorio.registrarMecanico(new Mecanico(nombre, cedula, especializacion));
    }
}