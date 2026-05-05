package co.edu.uniquindio.tallerbeta.service;

import co.edu.uniquindio.tallerbeta.model.Enum.Estado;
import co.edu.uniquindio.tallerbeta.model.entity.Cliente;
import co.edu.uniquindio.tallerbeta.model.entity.Mecanico;
import co.edu.uniquindio.tallerbeta.model.entity.Orden;
import co.edu.uniquindio.tallerbeta.repository.OrdenRepositorio;

import java.util.LinkedList;
import java.util.UUID;

public class OrdenServicio {

    private final OrdenRepositorio ordenRepositorio;

    public OrdenServicio() {
        this.ordenRepositorio = new OrdenRepositorio();
    }

    public Orden crearOrden(String instrucciones, Cliente cliente) {
        long activas = ordenRepositorio.listarOrdenes().stream()
                .filter(o -> o.getEstado() != Estado.FINALIZADO)
                .count();
        if (activas >= 10)
            throw new RuntimeException("No se pueden registrar más de 10 órdenes activas en el día.");

        Orden orden = cliente.realizarOrden(instrucciones);
        ordenRepositorio.registrarOrden(orden);
        return orden;
    }

    public void asignarMecanico(UUID idOrden, Mecanico mecanico) {
        Orden orden = buscarPorId(idOrden);
        if (mecanico.getOrdenAsignada() != null)
            throw new RuntimeException("El mecánico ya tiene una orden asignada.");
        orden.setMecanico(mecanico);
        orden.setEstado(Estado.ENPROCESO);
        mecanico.setOrdenAsignada(orden);
        ordenRepositorio.guardarDatos(ordenRepositorio.listarOrdenes());
    }

    public void finalizarOrden(UUID idOrden) {
        Orden orden = buscarPorId(idOrden);
        if (orden.getMecanico() == null)
            throw new RuntimeException("La orden no tiene mecánico asignado.");
        orden.getMecanico().realizarMantenimiento();
        ordenRepositorio.guardarDatos(ordenRepositorio.listarOrdenes());
    }

    public void eliminarOrden(UUID idOrden) {
        Orden orden = buscarPorId(idOrden);
        ordenRepositorio.eliminarOrden(orden);
    }

    public Orden buscarPorId(UUID id) {
        Orden orden = ordenRepositorio.buscarPorId(id);
        if (orden == null)
            throw new RuntimeException("No existe una orden con ese ID.");
        return orden;
    }

    public LinkedList<Orden> listarOrdenes() {
        return ordenRepositorio.listarOrdenes();
    }

    public LinkedList<Orden> listarOrdenesPorCliente(Cliente cliente) {
        LinkedList<Orden> resultado = new LinkedList<>();
        for (Orden o : ordenRepositorio.listarOrdenes()) {
            if (o.getCliente().getCedula().equals(cliente.getCedula())) {
                resultado.add(o);
            }
        }
        return resultado;
    }

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