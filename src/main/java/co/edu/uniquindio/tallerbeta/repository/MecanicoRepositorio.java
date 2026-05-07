package co.edu.uniquindio.tallerbeta.repository;

import co.edu.uniquindio.tallerbeta.model.entity.Mecanico;
import co.edu.uniquindio.tallerbeta.util.Constantes;
import co.edu.uniquindio.tallerbeta.util.Persistencia;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Repositorio para gestionar la persistencia de mecánicos en el sistema.
 * Maneja una lista de mecánicos y operaciones CRUD básicas con serialización a archivo.
 */
public class MecanicoRepositorio {
    private final LinkedList<Mecanico> listaMecanicos;

    /**
     * Constructor que inicializa el repositorio cargando los datos existentes.
     */
    public MecanicoRepositorio() {
        this.listaMecanicos = leerDatos();
    }

    /**
     * Registra un nuevo mecánico en la lista y guarda los datos.
     *
     * @param mecanico El mecánico a registrar.
     */
    public void registrarMecanico(Mecanico mecanico) {
        listaMecanicos.add(mecanico);
        guardarDatos(listaMecanicos);
    }

    /**
     * Elimina un mecánico de la lista y guarda los datos.
     *
     * @param mecanico El mecánico a eliminar.
     */
    public void eliminarMecanico(Mecanico mecanico) {
        listaMecanicos.remove(mecanico);
        guardarDatos(listaMecanicos);
    }

    /**
     * Retorna la lista completa de mecánicos.
     *
     * @return La lista de mecánicos.
     */
    public LinkedList<Mecanico> listarMecanicos() {
        return listaMecanicos;
    }

    /**
     * Guarda la lista de mecánicos en un archivo utilizando serialización.
     *
     * @param mecanicos La lista de mecánicos a guardar.
     */
    public void guardarDatos(LinkedList<Mecanico> mecanicos) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_MECANICO, mecanicos);
        } catch (IOException e) {
            System.err.println("Error guardando mecanicos: " + e.getMessage());
        }
    }

    /**
     * Lee los datos de mecánicos desde un archivo y retorna la lista.
     * Si ocurre un error, retorna una lista vacía.
     *
     * @return La lista de mecánicos leída.
     */
    public LinkedList<Mecanico> leerDatos() {
        try {
            List<Mecanico> datos = Persistencia.deserializarLista(Constantes.RUTA_MECANICO, Mecanico.class);
            if (datos != null) {
                return new LinkedList<>(datos);
            }
        } catch (Exception e) {
            System.err.println("Error cargando mecanicos: " + e.getMessage());
        }
        return new LinkedList<>();
    }
}