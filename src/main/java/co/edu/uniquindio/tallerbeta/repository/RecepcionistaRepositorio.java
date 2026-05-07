package co.edu.uniquindio.tallerbeta.repository;

import co.edu.uniquindio.tallerbeta.model.entity.Recepcionista;
import co.edu.uniquindio.tallerbeta.util.Constantes;
import co.edu.uniquindio.tallerbeta.util.Persistencia;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Repositorio para gestionar la persistencia de recepcionistas en el sistema.
 * Maneja una lista de recepcionistas y operaciones CRUD básicas con serialización a archivo.
 */
public class RecepcionistaRepositorio {
    private final LinkedList<Recepcionista> listaRecepcionistas;

    /**
     * Constructor que inicializa el repositorio cargando los datos existentes.
     */
    public RecepcionistaRepositorio() {
        this.listaRecepcionistas = leerDatos();
    }

    /**
     * Registra una nueva recepcionista en la lista y guarda los datos.
     *
     * @param recepcionista La recepcionista a registrar.
     */
    public void registrarRecepcionista(Recepcionista recepcionista) {
        listaRecepcionistas.add(recepcionista);
        guardarDatos(listaRecepcionistas);
    }

    /**
     * Elimina una recepcionista de la lista y guarda los datos.
     *
     * @param recepcionista La recepcionista a eliminar.
     */
    public void eliminarRecepcionista(Recepcionista recepcionista) {
        listaRecepcionistas.remove(recepcionista);
        guardarDatos(listaRecepcionistas);
    }

    /**
     * Retorna la lista completa de recepcionistas.
     *
     * @return La lista de recepcionistas.
     */
    public LinkedList<Recepcionista> listarRecepcionistas() {
        return listaRecepcionistas;
    }

    /**
     * Guarda la lista de recepcionistas en un archivo utilizando serialización.
     *
     * @param recepcionistas La lista de recepcionistas a guardar.
     */
    public void guardarDatos(LinkedList<Recepcionista> recepcionistas) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_RECEPCIONISTA, recepcionistas);
        } catch (IOException e) {
            System.err.println("Error guardando recepcionistas: " + e.getMessage());
        }
    }

    /**
     * Lee los datos de recepcionistas desde un archivo y retorna la lista.
     * Si ocurre un error, retorna una lista vacía.
     *
     * @return La lista de recepcionistas leída.
     */
    public LinkedList<Recepcionista> leerDatos() {
        try {
            List<Recepcionista> datos = Persistencia.deserializarLista(Constantes.RUTA_RECEPCIONISTA, Recepcionista.class);
            if (datos != null) {
                return new LinkedList<>(datos);
            }
        } catch (Exception e) {
            System.err.println("Error cargando recepcionistas: " + e.getMessage());
        }
        return new LinkedList<>();
    }
}