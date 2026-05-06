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

    public void actualizarInstrucciones(UUID idOrden, String nuevasInstrucciones) {
        Orden orden = buscarPorId(idOrden);
        if (orden.getEstado() != Estado.SINASIGNAR)
            throw new RuntimeException("Solo puedes editar ordenes sin asignar.");
        orden.setInstrucciones(nuevasInstrucciones);
        ordenRepositorio.guardarDatos(ordenRepositorio.listarOrdenes());
    }

    public void asignarMecanico(UUID idOrden, Mecanico mecanico) {
        Orden orden = buscarPorId(idOrden);
        if (mecanico.getOrdenAsignada() != null)
            throw new RuntimeException("El mecanico ya tiene una orden asignada.");
        orden.setMecanico(mecanico);
        orden.setEstado(Estado.ENPROCESO);
        mecanico.setOrdenAsignada(orden);
        ordenRepositorio.guardarDatos(ordenRepositorio.listarOrdenes());
    }

    public void finalizarOrden(UUID idOrden) {
        Orden orden = buscarPorId(idOrden);
        if (orden.getMecanico() == null)
            throw new RuntimeException("La orden no tiene mecanico asignado.");
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