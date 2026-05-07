package co.edu.uniquindio.tallerbeta.repository;

import co.edu.uniquindio.tallerbeta.model.entity.Cliente;
import co.edu.uniquindio.tallerbeta.util.Constantes;
import co.edu.uniquindio.tallerbeta.util.Persistencia;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Repositorio para gestionar la persistencia de clientes en el sistema.
 * Maneja una lista de clientes y operaciones CRUD básicas con serialización a archivo.
 */
public class ClienteRepositorio {
    private final LinkedList<Cliente> listaclientes;

    /**
     * Constructor que inicializa el repositorio cargando los datos existentes.
     */
    public ClienteRepositorio(){
        this.listaclientes = leerDatos();
    }

    /**
     * Registra un nuevo cliente en la lista y guarda los datos.
     *
     * @param cliente El cliente a registrar.
     */
    public void registrarCliente(Cliente cliente){
        listaclientes.add(cliente);
        guardarDatos(listaclientes);
    }

    /**
     * Elimina un cliente de la lista y guarda los datos.
     *
     * @param cliente El cliente a eliminar.
     */
    public void eliminarCliente(Cliente cliente){
        listaclientes.remove(cliente);
        guardarDatos(listaclientes);
    }

    /**
     * Retorna la lista completa de clientes.
     *
     * @return La lista de clientes.
     */
    public LinkedList<Cliente> listarClientes(){
        return listaclientes;
    }

    /**
     * Guarda la lista de clientes en un archivo utilizando serialización.
     *
     * @param clientes La lista de clientes a guardar.
     */
    public void guardarDatos(LinkedList<Cliente> clientes) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_CLIENTE, clientes);
        } catch (IOException e) {
            System.err.println("Error guardando pacientes: " + e.getMessage());
        }
    }


    /**
     * Lee los datos de clientes desde un archivo y retorna la lista.
     * Si ocurre un error, retorna una lista vacía.
     *
     * @return La lista de clientes leída.
     */
    public LinkedList<Cliente> leerDatos() {
        try {
            List<Cliente> datos = Persistencia.deserializarLista(Constantes.RUTA_CLIENTE, Cliente.class);
            if (datos != null) {
                return new LinkedList<>(datos);
            }
        } catch (Exception e) {
            System.err.println("Error cargando pacientes: " + e.getMessage());
        }
        return new LinkedList<>();
    }
}
