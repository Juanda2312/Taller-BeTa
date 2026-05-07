package co.edu.uniquindio.tallerbeta.service;

import co.edu.uniquindio.tallerbeta.model.Enum.Especializacion;
import co.edu.uniquindio.tallerbeta.model.entity.Mecanico;
import co.edu.uniquindio.tallerbeta.model.entity.Orden;
import co.edu.uniquindio.tallerbeta.repository.MecanicoRepositorio;

import java.util.LinkedList;

/**
 * Servicio que proporciona la lógica de negocio para la gestión de mecánicos.
 * Permite buscar mecánicos disponibles, asignar órdenes y realizar mantenimientos.
 */
public class MecanicoServicio {

    private final MecanicoRepositorio mecanicoRepositorio;

    /**
     * Constructor que inicializa el servicio con un repositorio de mecánicos.
     */
    public MecanicoServicio() {
        this.mecanicoRepositorio = new MecanicoRepositorio();
    }

    /**
     * Busca el primer mecánico disponible (sin orden asignada).
     *
     * @return El mecánico disponible encontrado.
     * @throws RuntimeException Si no hay mecánicos disponibles.
     */
    public Mecanico buscarMecanicoDisponible() {
        for (Mecanico m : mecanicoRepositorio.listarMecanicos()) {
            if (m.getOrdenAsignada() == null) return m;
        }
        throw new RuntimeException("No hay mecanicos disponibles.");
    }

    /**
     * Busca un mecánico disponible con la especialización especificada.
     *
     * @param especializacion La especialización requerida.
     * @return El mecánico disponible con la especialización.
     * @throws RuntimeException Si no hay mecánicos disponibles con esa especialización.
     */
    public Mecanico buscarMecanicoDisponiblePorEspecializacion(Especializacion especializacion) {
        for (Mecanico m : mecanicoRepositorio.listarMecanicos()) {
            if (m.getOrdenAsignada() == null && m.getEspecializacion() == especializacion) {
                return m;
            }
        }
        throw new RuntimeException("No hay mecanicos disponibles con esa especializacion.");
    }

    /**
     * Realiza el mantenimiento para el primer mecánico que tenga una orden asignada.
     * Finaliza la orden y guarda los datos.
     *
     * @return La orden finalizada.
     * @throws RuntimeException Si no hay mecánicos con orden asignada.
     */
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

    /**
     * Retorna la lista completa de mecánicos.
     *
     * @return La lista de mecánicos.
     */
    public LinkedList<Mecanico> listarMecanicos() {
        return mecanicoRepositorio.listarMecanicos();
    }

    /**
     * Retorna la lista de mecánicos disponibles (sin orden asignada).
     *
     * @return La lista de mecánicos disponibles.
     */
    public LinkedList<Mecanico> listarMecanicosDisponibles() {
        LinkedList<Mecanico> disponibles = new LinkedList<>();
        for (Mecanico m : mecanicoRepositorio.listarMecanicos()) {
            if (m.getOrdenAsignada() == null) disponibles.add(m);
        }
        return disponibles;
    }

    /**
     * Registra un nuevo mecánico en el sistema.
     * Verifica que no exista otro mecánico con la misma cédula.
     *
     * @param nombre El nombre del mecánico.
     * @param cedula La cédula del mecánico.
     * @param especializacion La especialización del mecánico.
     * @throws Exception Si ya existe un mecánico con esa cédula.
     */
    public void registrarMecanico(String nombre, String cedula,
                                  Especializacion especializacion) throws Exception {
        for (Mecanico m : mecanicoRepositorio.listarMecanicos()) {
            if (m.getCedula().equals(cedula))
                throw new Exception("Ya existe un mecanico con esa cedula.");
        }
        mecanicoRepositorio.registrarMecanico(new Mecanico(nombre, cedula, especializacion));
    }
}