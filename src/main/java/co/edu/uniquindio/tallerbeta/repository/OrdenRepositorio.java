package co.edu.uniquindio.tallerbeta.repository;

import co.edu.uniquindio.tallerbeta.model.entity.Orden;
import co.edu.uniquindio.tallerbeta.util.Constantes;
import co.edu.uniquindio.tallerbeta.util.Persistencia;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Repositorio para gestionar la persistencia de órdenes en el sistema.
 * Maneja una lista de órdenes y operaciones CRUD básicas con serialización a archivo.
 */
public class OrdenRepositorio {
    private final LinkedList<Orden> listaOrdenes;

    /**
     * Constructor que inicializa el repositorio cargando los datos existentes.
     */
    public OrdenRepositorio() {
        this.listaOrdenes = leerDatos();
    }

    /**
     * Registra una nueva orden en la lista y guarda los datos.
     *
     * @param orden La orden a registrar.
     */
    public void registrarOrden(Orden orden) {
        listaOrdenes.add(orden);
        guardarDatos(listaOrdenes);
    }

    /**
     * Elimina una orden de la lista y guarda los datos.
     *
     * @param orden La orden a eliminar.
     */
    public void eliminarOrden(Orden orden) {
        listaOrdenes.remove(orden);
        guardarDatos(listaOrdenes);
    }

    /**
     * Busca una orden por su ID único.
     *
     * @param id El ID de la orden a buscar.
     * @return La orden encontrada o null si no existe.
     */
    public Orden buscarPorId(UUID id) {
        return listaOrdenes.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Retorna la lista completa de órdenes.
     *
     * @return La lista de órdenes.
     */
    public LinkedList<Orden> listarOrdenes() {
        return listaOrdenes;
    }

    /**
     * Guarda la lista de órdenes en un archivo utilizando serialización.
     *
     * @param ordenes La lista de órdenes a guardar.
     */
    public void guardarDatos(LinkedList<Orden> ordenes) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_ORDEN, ordenes);
        } catch (IOException e) {
            System.err.println("Error guardando ordenes: " + e.getMessage());
        }
    }

    /**
     * Lee los datos de órdenes desde un archivo y retorna la lista.
     * Si ocurre un error, retorna una lista vacía.
     *
     * @return La lista de órdenes leída.
     */
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