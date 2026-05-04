package co.edu.uniquindio.tallerbeta.repository;

import co.edu.uniquindio.tallerbeta.model.entity.Orden;
import co.edu.uniquindio.tallerbeta.util.Constantes;
import co.edu.uniquindio.tallerbeta.util.Persistencia;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class OrdenRepositorio {
    private final LinkedList<Orden> listaOrdenes;

    public OrdenRepositorio() {
        this.listaOrdenes = leerDatos();
    }

    public void registrarOrden(Orden orden) {
        listaOrdenes.add(orden);
        guardarDatos(listaOrdenes);
    }

    public void eliminarOrden(Orden orden) {
        listaOrdenes.remove(orden);
        guardarDatos(listaOrdenes);
    }

    public Orden buscarPorId(UUID id) {
        return listaOrdenes.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public LinkedList<Orden> listarOrdenes() {
        return listaOrdenes;
    }

    public void guardarDatos(LinkedList<Orden> ordenes) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_ORDEN, ordenes);
        } catch (IOException e) {
            System.err.println("Error guardando ordenes: " + e.getMessage());
        }
    }

    public LinkedList<Orden> leerDatos() {
        try {
            List<Orden> datos = Persistencia.deserializarLista(Constantes.RUTA_ORDEN, Orden.class);
            if (datos != null) {
                return new LinkedList<>(datos);
            }
        } catch (Exception e) {
            System.err.println("Error cargando ordenes: " + e.getMessage());
        }
        return new LinkedList<>();
    }
}