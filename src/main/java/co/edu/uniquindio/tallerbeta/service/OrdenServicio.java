package co.edu.uniquindio.tallerbeta.service;

import co.edu.uniquindio.tallerbeta.model.Enum.Estado;
import co.edu.uniquindio.tallerbeta.model.entity.Cliente;
import co.edu.uniquindio.tallerbeta.model.entity.Mecanico;
import co.edu.uniquindio.tallerbeta.model.entity.Orden;
import co.edu.uniquindio.tallerbeta.repository.OrdenRepositorio;

import java.util.LinkedList;
import java.util.UUID;

/**
 * Servicio que proporciona la lógica de negocio para la gestión de órdenes de mantenimiento.
 * Permite crear, asignar, actualizar y finalizar órdenes, además de consultas.
 */
public class OrdenServicio {

    private final OrdenRepositorio ordenRepositorio;

    /**
     * Constructor que inicializa el servicio con un repositorio de órdenes.
     */
    public OrdenServicio() {
        this.ordenRepositorio = new OrdenRepositorio();
    }

    /**
     * Crea una nueva orden de mantenimiento para un cliente.
     * Verifica que no haya más de 10 órdenes activas (regla de negocio RN-02).
     *
     * @param instrucciones Las instrucciones de la orden.
     * @param cliente El cliente que solicita la orden.
     * @return La orden creada.
     * @throws RuntimeException Si hay más de 10 órdenes activas.
     */
    public Orden crearOrden(String instrucciones, Cliente cliente) {
        int activas = 0;
        for (Orden o : ordenRepositorio.listarOrdenes()) {
            if (o.getEstado() != Estado.FINALIZADO) activas++;
        }
        if (activas >= 10)
            throw new RuntimeException("No se pueden registrar mas de 10 ordenes activas en el dia. (RN-02)");

        Orden orden = cliente.realizarOrden(instrucciones);
        ordenRepositorio.registrarOrden(orden);
        return orden;
    }

    /**
     * Actualiza las instrucciones de una orden.
     * Solo permite editar órdenes sin asignar.
     *
     * @param idOrden El ID de la orden a actualizar.
     * @param nuevasInstrucciones Las nuevas instrucciones.
     * @throws RuntimeException Si la orden no está sin asignar.
     */
    public void actualizarInstrucciones(UUID idOrden, String nuevasInstrucciones) {
        Orden orden = buscarPorId(idOrden);
        if (orden.getEstado() != Estado.SINASIGNAR)
            throw new RuntimeException("Solo puedes editar ordenes sin asignar.");
        orden.setInstrucciones(nuevasInstrucciones);
        ordenRepositorio.guardarDatos(ordenRepositorio.listarOrdenes());
    }

    /**
     * Asigna un mecánico a una orden.
     * Cambia el estado de la orden a ENPROCESO.
     *
     * @param idOrden El ID de la orden.
     * @param mecanico El mecánico a asignar.
     * @throws RuntimeException Si el mecánico ya tiene una orden asignada.
     */
    public void asignarMecanico(UUID idOrden, Mecanico mecanico) {
        Orden orden = buscarPorId(idOrden);
        if (mecanico.getOrdenAsignada() != null)
            throw new RuntimeException("El mecanico ya tiene una orden asignada.");
        orden.setMecanico(mecanico);
        orden.setEstado(Estado.ENPROCESO);
        mecanico.setOrdenAsignada(orden);
        ordenRepositorio.guardarDatos(ordenRepositorio.listarOrdenes());
    }

    /**
     * Finaliza una orden realizando el mantenimiento.
     *
     * @param idOrden El ID de la orden a finalizar.
     * @throws RuntimeException Si la orden no tiene mecánico asignado.
     */
    public void finalizarOrden(UUID idOrden) {
        Orden orden = buscarPorId(idOrden);
        if (orden.getMecanico() == null)
            throw new RuntimeException("La orden no tiene mecanico asignado.");
        orden.getMecanico().realizarMantenimiento();
        ordenRepositorio.guardarDatos(ordenRepositorio.listarOrdenes());
    }

    /**
     * Elimina una orden del sistema.
     *
     * @param idOrden El ID de la orden a eliminar.
     */
    public void eliminarOrden(UUID idOrden) {
        Orden orden = buscarPorId(idOrden);
        ordenRepositorio.eliminarOrden(orden);
    }

    /**
     * Busca una orden por su ID.
     *
     * @param id El ID de la orden.
     * @return La orden encontrada.
     * @throws RuntimeException Si no existe una orden con ese ID.
     */
    public Orden buscarPorId(UUID id) {
        Orden orden = ordenRepositorio.buscarPorId(id);
        if (orden == null)
            throw new RuntimeException("No existe una orden con ese ID.");
        return orden;
    }

    /**
     * Retorna la lista completa de órdenes.
     *
     * @return La lista de órdenes.
     */
    public LinkedList<Orden> listarOrdenes() {
        return ordenRepositorio.listarOrdenes();
    }

    /**
     * Retorna la lista de órdenes de un cliente específico.
     *
     * @param cliente El cliente cuyas órdenes se buscan.
     * @return La lista de órdenes del cliente.
     */
    public LinkedList<Orden> listarOrdenesPorCliente(Cliente cliente) {
        LinkedList<Orden> resultado = new LinkedList<>();
        for (Orden o : ordenRepositorio.listarOrdenes()) {
            if (o.getCliente().getCedula().equals(cliente.getCedula())) {
                resultado.add(o);
            }
        }
        return resultado;
    }

    /**
     * Retorna la lista de órdenes con un estado específico.
     *
     * @param estado El estado de las órdenes a listar.
     * @return La lista de órdenes con ese estado.
     */
    public LinkedList<Orden> listarOrdenesPorEstado(Estado estado) {
        LinkedList<Orden> resultado = new LinkedList<>();
        for (Orden o : ordenRepositorio.listarOrdenes()) {
            if (o.getEstado() == estado) {
                resultado.add(o);
            }
        }
        return resultado;
    }
}