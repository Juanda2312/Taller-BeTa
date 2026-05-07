package co.edu.uniquindio.tallerbeta.repository;

import co.edu.uniquindio.tallerbeta.model.entity.Administrador;
import co.edu.uniquindio.tallerbeta.util.Constantes;
import co.edu.uniquindio.tallerbeta.util.Persistencia;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Repositorio para gestionar la persistencia de administradores en el sistema.
 * Maneja una lista de administradores y operaciones CRUD básicas con serialización a archivo.
 */
public class AdministradorRepositorio {
    private final LinkedList<Administrador> listaAdministradores;

    /**
     * Constructor que inicializa el repositorio cargando los datos existentes.
     */
    public AdministradorRepositorio() {
        this.listaAdministradores = leerDatos();
    }

    /**
     * Registra un nuevo administrador en la lista y guarda los datos.
     *
     * @param administrador El administrador a registrar.
     */
    public void registrarAdministrador(Administrador administrador) {
        listaAdministradores.add(administrador);
        guardarDatos(listaAdministradores);
    }

    /**
     * Elimina un administrador de la lista y guarda los datos.
     *
     * @param administrador El administrador a eliminar.
     */
    public void eliminarAdministrador(Administrador administrador) {
        listaAdministradores.remove(administrador);
        guardarDatos(listaAdministradores);
    }

    /**
     * Retorna la lista completa de administradores.
     *
     * @return La lista de administradores.
     */
    public LinkedList<Administrador> listarAdministradores() {
        return listaAdministradores;
    }

    /**
     * Guarda la lista de administradores en un archivo utilizando serialización.
     *
     * @param administradores La lista de administradores a guardar.
     */
    public void guardarDatos(LinkedList<Administrador> administradores) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_ADMINISTRADOR, administradores);
        } catch (IOException e) {
            System.err.println("Error guardando administradores: " + e.getMessage());
        }
    }

    /**
     * Lee los datos de administradores desde un archivo y retorna la lista.
     * Si ocurre un error, retorna una lista vacía.
     *
     * @return La lista de administradores leída.
     */
    public LinkedList<Administrador> leerDatos() {
        try {
            List<Administrador> datos = Persistencia.deserializarLista(Constantes.RUTA_ADMINISTRADOR, Administrador.class);
            if (datos != null) {
                return new LinkedList<>(datos);
            }
        } catch (Exception e) {
            System.err.println("Error cargando administradores: " + e.getMessage());
        }
        return new LinkedList<>();
    }
}