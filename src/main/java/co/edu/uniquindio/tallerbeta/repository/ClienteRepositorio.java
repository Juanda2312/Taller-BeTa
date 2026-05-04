package co.edu.uniquindio.tallerbeta.repository;

import co.edu.uniquindio.tallerbeta.model.entity.Cliente;
import co.edu.uniquindio.tallerbeta.util.Constantes;
import co.edu.uniquindio.tallerbeta.util.Persistencia;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ClienteRepositorio {
    private final LinkedList<Cliente> listaclientes;

    public ClienteRepositorio(){
        this.listaclientes = leerDatos();
    }

    public void registrarCliente(Cliente cliente){
        listaclientes.add(cliente);
        guardarDatos(listaclientes);
    }

    public void eliminarCliente(Cliente cliente){
        listaclientes.remove(cliente);
        guardarDatos(listaclientes);
    }

    public LinkedList<Cliente> listarClientes(){
        return listaclientes;
    }

    public void guardarDatos(LinkedList<Cliente> clientes) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_CLIENTE, clientes);
        } catch (IOException e) {
            System.err.println("Error guardando pacientes: " + e.getMessage());
        }
    }


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
