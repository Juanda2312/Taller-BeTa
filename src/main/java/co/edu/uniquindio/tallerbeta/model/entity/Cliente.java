package co.edu.uniquindio.tallerbeta.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.LinkedList;


/**
 * Clase que representa a un cliente en el sistema del taller.
 * Un cliente es un tipo de usuario que puede tener vehículos y órdenes de mantenimiento asociadas.
 */
@Getter(onMethod_ = @JsonIgnore)
@Setter

public class Cliente extends Usuario {

    LinkedList<Vehiculo> ListaVehiculos;

    @JsonIgnore
    LinkedList<Orden> ListaOrdenes;

    /**
     * Constructor por defecto que inicializa las listas de vehículos y órdenes.
     */
    public Cliente(){
        super();
        this.ListaVehiculos = new LinkedList<>();
        this.ListaOrdenes = new LinkedList<>();
    }

    /**
     * Constructor que inicializa el cliente con nombre, cédula, correo y contraseña,
     * además de inicializar las listas de vehículos y órdenes.
     *
     * @param nombre El nombre del cliente.
     * @param cedula La cédula del cliente.
     * @param correo El correo electrónico del cliente.
     * @param contrasenia La contraseña del cliente.
     */
    public Cliente(String nombre, String cedula, String correo, String contrasenia) {
        super(nombre, cedula, correo, contrasenia);
        this.ListaVehiculos = new LinkedList<>();
        this.ListaOrdenes = new LinkedList<>();
    }

    /**
     * Método que permite al cliente realizar una nueva orden de mantenimiento.
     * Crea una nueva orden con las instrucciones proporcionadas y la asocia al cliente.
     *
     * @param instrucciones Las instrucciones para la orden de mantenimiento.
     * @return La orden creada.
     */
    public Orden realizarOrden(String instrucciones) {
        Orden orden = new Orden(instrucciones, this);
        this.ListaOrdenes.add(orden);
        return orden;
    }
}