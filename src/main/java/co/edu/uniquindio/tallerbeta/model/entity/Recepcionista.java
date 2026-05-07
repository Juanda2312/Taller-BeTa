package co.edu.uniquindio.tallerbeta.model.entity;

import lombok.Getter;
import lombok.Setter;
import java.util.LinkedList;

/**
 * Clase que representa a un recepcionista en el sistema del taller.
 * Un recepcionista es un tipo de persona que atiende a clientes y mantiene una lista de clientes atendidos.
 */
@Getter
@Setter
public class Recepcionista extends Persona {

    LinkedList<Cliente> clientesatendidos;

    /**
     * Constructor por defecto que inicializa la lista de clientes atendidos.
     */
    public Recepcionista(){
        super();
        clientesatendidos = new LinkedList<>();
    }

    /**
     * Constructor que inicializa el recepcionista con nombre y cédula,
     * además de inicializar la lista de clientes atendidos.
     *
     * @param nombre El nombre del recepcionista.
     * @param cedula La cédula del recepcionista.
     */
    public Recepcionista(String nombre, String cedula) {
        super(nombre, cedula);
        clientesatendidos = new LinkedList<>();
    }

    /**
     * Método que permite al recepcionista atender a un cliente.
     * Agrega el cliente a la lista de clientes atendidos si no está ya presente.
     *
     * @param cliente El cliente a atender.
     */
    public void AtenderCliente(Cliente cliente) {
        if (!clientesatendidos.contains(cliente)) {
            clientesatendidos.add(cliente);
        }
    }
}